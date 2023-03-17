package com.mx.tool

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * @author Mx
 * @date 2023/03/17
 */

fun RecyclerView.ViewHolder.getColor(@ColorRes id: Int) = itemView.context.getColor(id)

fun RecyclerView.ViewHolder.getString(@StringRes id: Int) = itemView.context.getString(id)

fun RecyclerView.ViewHolder.getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(itemView.context, id)