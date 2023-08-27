package com.mq.lib.mvp.lifecycle

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

/**
 * 这里继承LifecycleRegistry是复用Lifecycle的 Observer管理功能
 */
class MVPLifecycleRegistry(provider: LifecycleOwner, private val reset: Boolean = false) :
    LifecycleRegistry(provider) {
    private val mObserverList = mutableListOf<LifecycleObserver>()

    internal fun onCreate() {
        handleLifecycleEvent(Event.ON_CREATE)
    }

    override fun handleLifecycleEvent(event: Event) {
        super.handleLifecycleEvent(event)
        if (event == Event.ON_DESTROY) {
            if (reset) {
                resetObserver()
            } else {
                clear()
            }
        }
    }

    internal fun onDestroy() {
        handleLifecycleEvent(Event.ON_DESTROY)
    }

    internal fun clear() {
        val iterator = mObserverList.iterator()
        while (iterator.hasNext()) {
            val observer = iterator.next()
            super.removeObserver(observer)
            iterator.remove()
        }
    }

    private fun resetObserver() {
        mutableListOf<LifecycleObserver>().run {
            addAll(mObserverList)
            forEach { removeObserver(it) }
            this@MVPLifecycleRegistry.currentState = State.INITIALIZED
            forEach { addObserver(it) }
        }
    }

    @MainThread
    override fun addObserver(observer: LifecycleObserver) {
        super.addObserver(observer)
        mObserverList.add(observer)
    }

    @MainThread
    override fun removeObserver(observer: LifecycleObserver) {
        super.removeObserver(observer)
        mObserverList.remove(observer)
    }
}