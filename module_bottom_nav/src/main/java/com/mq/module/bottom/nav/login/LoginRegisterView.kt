package com.mq.module.bottom.nav.login

import com.mq.lib.mvp.IBaseView

interface LoginRegisterView : LoginView, RegisterView, IBaseView {

    override fun loginSuccess() {

    }

    override fun registerSuccess() {

    }
}