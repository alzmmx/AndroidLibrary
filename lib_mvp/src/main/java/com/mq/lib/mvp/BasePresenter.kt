package com.mq.lib.mvp

import android.util.Log
import com.mq.lib.mvp.lifecycle.MVPLifecycleOwner
import com.mq.lib.mvp.lifecycle.MVPLifecycleObserver
import java.lang.ref.WeakReference

abstract class BasePresenter<V : IBaseView> : IBasePresenter<V>, BaseViewModel() {
    private var viewWeakReference: WeakReference<V>? = null

    protected val view: V?
        get() {
            return viewWeakReference?.get()
        }

    final override fun attachView(v: V) {
        viewWeakReference = WeakReference(v)
        if (v is MVPLifecycleOwner) {
            v.getViewLifecycle().addObserver(object : MVPLifecycleObserver() {
                override fun onCreate() {
                    Log.d("TAG","--------->onCreate")
                    reAttachView(v)
                }

                override fun onDestroy() {
                    Log.d("TAG","--------->onDestroy")
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
}