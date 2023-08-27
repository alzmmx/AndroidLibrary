package com.mq.module.bottom.nav.login

import android.util.Log
import com.mq.lib.mvp.BasePresenter

class LoginPresenter : BasePresenter<LoginRegisterView>() {

    fun login() {
        Log.d("TAG", "----------->login=$view")
        view?.loginSuccess()
    }

    fun register() {
        Log.d("TAG", "----------->register=$view")
        view?.registerSuccess()
    }

    fun test() {
        Log.d("TAG", "----------->test=$view")

    }
}

class CheckPresenter : BasePresenter<CheckView>() {

    fun check() {
        view?.check()
    }

}