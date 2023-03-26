package com.mq.module.bottom.nav.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _times = MutableLiveData<List<Long>>()
    val timesLiveData: LiveData<List<Long>> = _times

    fun createTimes() {
        val cur = System.currentTimeMillis()
        val times = mutableListOf<Long>()
        for (i in 0..100) {
            times.add(cur - i * 3600 * 1000)
        }
        _times.value = times
    }
}