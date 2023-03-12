package com.mq.lib.mvp

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlin.reflect.KClass


class VMPresenterLazy<V : IBaseView, P : BasePresenter<V>>(
    private val viewModelClass: KClass<P>,
    private val v: V,
    private val storeProducer: () -> ViewModelStore,
    private val factoryProducer: () -> ViewModelProvider.Factory
) : Lazy<P> {
    private var cached: P? = null

    override val value: P
        get() {
            val viewModel = cached
            return if (viewModel == null) {
                val factory = factoryProducer()
                val store = storeProducer()
                ViewModelProvider(store, factory)[viewModelClass.java].also {
                    cached = it
                    it.attachView(v)
                }
            } else {
                viewModel
            }
        }

    override fun isInitialized(): Boolean = cached != null
}