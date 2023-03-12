package com.mq.lib.mvp.lifecycle

interface MVPLifecycleOwner{

    fun getViewLifecycle(): MVPLifecycleRegistry
}