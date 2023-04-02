package com.mq.module.bottom.nav.ui.home

import retrofit2.http.GET

interface Api {

    @GET("/videos")
    suspend fun getData(): MutableList<VideoBean>
}