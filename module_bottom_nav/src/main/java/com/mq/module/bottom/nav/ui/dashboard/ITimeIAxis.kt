package com.mq.module.bottom.nav.ui.dashboard

interface ITimeIAxis {

    fun getText(position: Int): String
    fun getGroupName(position: Int): String
    fun firstInGroup(position: Int): Boolean
}