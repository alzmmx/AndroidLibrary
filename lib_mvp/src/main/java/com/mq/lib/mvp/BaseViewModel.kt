package com.mq.lib.mvp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlin.coroutines.cancellation.CancellationException
import kotlin.experimental.ExperimentalTypeInference

open class BaseViewModel : ViewModel() {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onUntreatedError(throwable)
    }

    open fun onUntreatedError(throwable: Throwable) {

    }

    protected fun launchTry(
        block: suspend CoroutineScope.() -> Unit,
        catch: (suspend CoroutineScope.(Throwable) -> Unit)? = null,
        finally: (suspend CoroutineScope.() -> Unit)? = null,
        handleCancellationExceptionManually: Boolean = false,
        coroutineExceptionHandler: CoroutineExceptionHandler = exceptionHandler
    ): Job {
        return viewModelScope.launch(coroutineExceptionHandler) {
            try {
                block()
            } catch (e: Exception) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
                if (e !is CancellationException || handleCancellationExceptionManually) {
                    catch?.invoke(this, e)
                } else {
                    throw e
                }
            } finally {
                finally?.invoke(this)
            }
        }
    }

    protected fun launch(
        coroutineExceptionHandler: CoroutineExceptionHandler = exceptionHandler,
        block: suspend CoroutineScope.() -> Unit
    ) : Job {
        return viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }
}