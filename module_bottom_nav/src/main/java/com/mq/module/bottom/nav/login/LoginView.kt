package com.mq.module.bottom.nav.login

import com.mq.lib.mvp.IBaseView

interface LoginView :IBaseView{

    fun loginSuccess()
}

interface CheckView :IBaseView{

    fun check()
}