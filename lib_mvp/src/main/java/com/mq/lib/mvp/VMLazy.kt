package com.mq.lib.mvp

import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.reflect.KClass

@MainThread
inline fun <reified V : IBaseView, reified P : BasePresenter<V>> ComponentActivity.presenters(
    v: V,
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<P> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    return VMPresenterLazy(P::class, v, { viewModelStore }, factoryPromise)
}

@MainThread
inline fun <reified V : IBaseView, reified VM : BasePresenter<V>> Fragment.presenters(
    v: V,
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> = createViewModelLazy(
    VM::class,
    v,
    { ownerProducer().viewModelStore },
    factoryProducer ?: {
        (ownerProducer() as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory
            ?: defaultViewModelProviderFactory
    }
)

@MainThread
fun <V : IBaseView, P : BasePresenter<V>> Fragment.createViewModelLazy(
    viewModelClass: KClass<P>,
    v: V,
    storeProducer: () -> ViewModelStore,
    factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<P> {
    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    return VMPresenterLazy(viewModelClass, v, storeProducer, factoryPromise)
}