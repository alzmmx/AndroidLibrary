package com.mx.ui.statelayout

/**
 *
 * @author Mx
 * @date 2023/03/08
 */
class State private constructor(val value: Int) {
    companion object {
        val CONTENT = State(0)
        val EMPTY = State(1)
        val LOADING = State(2)

        @JvmStatic
        @kotlin.jvm.Throws(IllegalArgumentException::class)
        fun create(state: Int): State {
            if (state == 0) {
                throw java.lang.IllegalArgumentException("state cannot be the same as content(0)")
            }
            return State(state)
        }
    }
}