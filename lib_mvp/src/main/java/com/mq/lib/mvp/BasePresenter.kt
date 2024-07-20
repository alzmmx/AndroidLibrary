package com.mq.lib.mvp

import com.mq.lib.mvp.lifecycle.MVPLifecycleObserver
import com.mq.lib.mvp.lifecycle.MVPLifecycleOwner
import java.lang.ref.WeakReference

abstract class BasePresenter<V : IBaseView> : IBasePresenter<V>, BaseViewModel() {
    private var viewWeakReference: WeakReference<V>? = null

    @Volatile
    private var isDetach = false

    protected val view get() = viewWeakReference?.get()


    final override fun attachView(v: V) {
        if (v is MVPLifecycleOwner) {
            v.getViewLifecycle().addObserver(object : MVPLifecycleObserver() {
                override fun onCreate() {
                    realAttachView(v)
                }

                override fun onDestroy() {
                    detachView()
                }
            })
        } else {
            realAttachView(v)
        }
    }

    private fun realAttachView(v: V) {
        if (viewWeakReference == null) {
            viewWeakReference = WeakReference(v)
            onAttach()
            isDetach = false
        }
    }

    final override fun detachView() {
        viewWeakReference?.clear()
        viewWeakReference = null
        if (!isDetach) {
            isDetach = true
            onDetach()
        }
    }


    override fun isAttach() = view != null

    open fun onAttach() {}
    open fun onDetach() {}

    override fun onCleared() {
        super.onCleared()
        detachView()
    }
}