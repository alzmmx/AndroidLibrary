package com.mx.tool.ktx


import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

/**
 *
 * @author Mx
 * @date 2022/10/21
 */
private var lastClickTime: Long = 0L

fun View.fastClick(): Boolean {
    val curT = System.currentTimeMillis()
    if (curT - lastClickTime <= 500) return true
    lastClickTime = curT
    return false
}

fun View.singleOnClick(listener: (View) -> Unit, repeatCallback: (() -> Unit)? = null) {
    this.setOnClickListener {
        if (fastClick()) {
            repeatCallback?.invoke()
        } else {
            listener(it)
        }
    }
}

fun View.setOnSingleClickListener(listener: OnClickListener, repeatCallback: (() -> Unit)? = null) {
    this.setOnClickListener {
        if (fastClick()) {
            repeatCallback?.invoke()
        } else {
            listener.onClick(it)
        }
    }
}

fun View.setOnSingleClickListener(listener: OnClickListener) {
    this.setOnClickListener {
        if (fastClick()) {
            return@setOnClickListener
        } else {
            listener.onClick(it)
        }
    }
}

fun View.getFocus() {
    isFocusable = true
    isFocusableInTouchMode = true
    requestFocus()
}

fun View.getString(@StringRes id: Int): String {
    return context.getString(id)
}

@ColorInt
fun View.getColor(@ColorRes id: Int): Int {
    return context.getColor(id)
}

fun View.setViewWidth(w: Int) {
    this.layoutParams = this.layoutParams.apply { width = w }
}

fun View.setViewHeight(h: Int) {
    this.layoutParams = this.layoutParams.apply { height = h }
}

fun View.setWidthAndHeight(w: Int, h: Int) {
    this.layoutParams = this.layoutParams.apply {
        width = w
        height = h
    }
}

fun View.setMarginTop(marginTop: Int) {
    this.layoutParams = (this.layoutParams as MarginLayoutParams).apply {
        topMargin = marginTop
    }
}

/**
 * 根据前一个报价判断涨跌
 */
fun View.isUpByTag(price: Long): Boolean? {
    val prePrice = this.tag as Long?
    this.tag = price
    if (prePrice == null) {
        return null
    }
    return price > prePrice
}