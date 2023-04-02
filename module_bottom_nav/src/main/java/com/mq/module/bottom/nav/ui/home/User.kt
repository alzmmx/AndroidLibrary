package com.mq.module.bottom.nav.ui.home

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter=true)
data class User(val name:String="MX")
