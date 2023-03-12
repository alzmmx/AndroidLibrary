package com.mq.lib.mvp

import com.mq.lib.mvp.lifecycle.MVPLifecycleObserver
import com.mq.lib.mvp.lifecycle.MVPLifecycleOwner
import java.lang.ref.WeakReference

abstract class BasePresenter<V : IBaseView> : IBasePresenter<V>, BaseViewModel() {
    private var viewWeakReference: WeakReference<V>? = null

    protected val view get() = viewWeakReference?.get()


    final override fun attachView(v: V) {
        viewWeakReference = WeakReference(v)
        if (v is MVPLifecycleOwner) {
            v.getViewLifecycle().addObserver(object : MVPLifecycleObserver() {
                override fun onCreate() {
                    reAttachView(v)
                }

                override fun onDestroy() {
                    detachView()
                }
            })
        }
    }

    private fun reAttachView(v: V) {
        if (viewWeakReference == null) {
            viewWeakReference = WeakReference(v)
        }
    }

    final override fun detachView() {
        viewWeakReference?.clear()
        viewWeakReference = null
    }

    override fun onCleared() {
        detachView()
    }
}