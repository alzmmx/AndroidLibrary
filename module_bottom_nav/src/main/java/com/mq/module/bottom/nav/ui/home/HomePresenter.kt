package com.mq.module.bottom.nav.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mq.lib.mvp.BasePresenter
import kotlinx.coroutines.launch

class HomePresenter : BasePresenter<HomeView>() {

    fun startLogin(context: Context) {
        //context.startActivity(Intent(context, LoginActivity::class.java))
        viewModelScope.launch {
            val data = RetrofitManager.getApi(Api::class.java).getData()
            Log.d("TAG", "----->data=${data}")
        }
    }
}