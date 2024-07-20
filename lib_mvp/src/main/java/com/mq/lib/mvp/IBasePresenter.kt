package com.mq.lib.mvp

interface IBasePresenter<V : IBaseView> {

    fun attachView(v: V)

    fun detachView()

    fun isAttach(): Boolean
}