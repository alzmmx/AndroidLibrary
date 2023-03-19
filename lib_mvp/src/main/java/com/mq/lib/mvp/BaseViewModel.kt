package com.mq.lib.mvp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlin.experimental.ExperimentalTypeInference

open class BaseViewModel : ViewModel() {
    @OptIn(ExperimentalTypeInference::class)
    public fun <T> flow(@BuilderInference block: suspend FlowCollector<T>.() -> Unit): Flow<T>? {
        return null
    }
}