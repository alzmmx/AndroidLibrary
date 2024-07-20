package com.mx.tool.ktx;

/**
 *
 * @author Mx
 * @date 2023/03/15
 */


fun Number.dp2px(): Int {
    return ScreenUtil.dp2px(this.toFloat())
}


fun Number.px2dp(): Float {
    return ScreenUtil.px2dp(this.toInt())
}

fun Number.sp2px(): Int {
    return ScreenUtil.sp2px(this.toFloat())
}

/**
 * 将dp值转化为px
 */
val Number.dp
    get() = this.toFloat().dp2px()

/**
 * 将sp值转化为px
 */
val Number.sp
    get() = this.toFloat().sp2px()

