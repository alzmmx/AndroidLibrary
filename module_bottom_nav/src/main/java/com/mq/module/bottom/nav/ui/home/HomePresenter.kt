package com.mq.module.bottom.nav.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mq.lib.mvp.BasePresenter
import com.mq.module.bottom.nav.login.LoginActivity
import kotlinx.coroutines.launch

class HomePresenter : BasePresenter<HomeView>() {

    fun startLogin(context: Context) {
        context.startActivity(Intent(context, LoginActivity::class.java))
        /*viewModelScope.launch {
        }*/
    }
}