package com.mq.module.bottom.nav.login

import com.mq.lib.mvp.VBActivity
import com.mq.lib.mvp.presenters
import com.mq.module.bottom.nav.databinding.ActivityLoginBinding

class LoginActivity : VBActivity<ActivityLoginBinding>(), LoginRegisterView {

    private val loginPresenter by presenters<LoginRegisterView, LoginPresenter>(this)
    override fun binding(): ActivityLoginBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
    }
}