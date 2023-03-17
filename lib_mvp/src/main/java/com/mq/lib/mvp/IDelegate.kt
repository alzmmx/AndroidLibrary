package com.mq.lib.mvp

/**
 *
 * @author Mx
 * @date 2023/02/27
 */
interface IDelegate: IBaseView {

    fun attach(view: IBaseView)

    fun detach()
}