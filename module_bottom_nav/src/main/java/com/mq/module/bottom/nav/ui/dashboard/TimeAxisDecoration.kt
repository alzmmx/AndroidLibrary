package com.mq.module.bottom.nav.ui.dashboard

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.max

class TimeAxisDecoration : RecyclerView.ItemDecoration() {

    private var marginStart = 100f
    private val linePaint = Paint().apply {
        color = Color.parseColor("#d1d1d1")
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
        strokeWidth = 2f
    }
    private val circlePaint = Paint().apply {
        color = Color.parseColor("#FF03DAC5")
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }
    private val textPaint = Paint().apply {
        color = Color.parseColor("#FF03DAC5")
        style = Paint.Style.FILL_AND_STROKE
        textSize = 30f
        isAntiAlias = true
    }

    private val paint = Paint().apply {
        color = Color.parseColor("#000000")
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }
    private val circlePaint2 = Paint().apply {
        color = Color.parseColor("#FFFFFF")
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }
    private val mHeight = 15
    private var radius = 12f

    private var maxTextHeight = 0f


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val str = (parent.adapter as ITimeIAxis).getText((view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition)
        val rect = Rect()
        textPaint.getTextBounds(str, 0, str.length, rect)
        maxTextHeight = max(maxTextHeight, rect.height().toFloat())
        outRect.set(marginStart.toInt(), maxTextHeight.toInt(), 0, 0)
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        val count = parent.childCount
        for (i in 0 until count) {
            val view = parent.getChildAt(i)
            canvas.drawLine(marginStart / 2, 0f, marginStart / 2, view.bottom.toFloat(), linePaint)
            canvas.drawLine(marginStart / 2, view.top.toFloat() - maxTextHeight / 2, marginStart, view.top.toFloat() - maxTextHeight / 2, linePaint)
            canvas.drawCircle(marginStart / 2, view.top.toFloat() - maxTextHeight / 2, radius, circlePaint)
            canvas.drawCircle(marginStart / 2, view.top.toFloat() - maxTextHeight / 2, radius - 4, circlePaint2)
            val str = (parent.adapter as ITimeIAxis).getText((view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition)
            canvas.drawText(str, marginStart, view.top.toFloat(), textPaint)
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        val itemCount = state.itemCount
        val childCount = parent.childCount
        val left = parent.left + parent.paddingLeft
        var preGroupName: String? //标记上一个item对应的Group

        var currentGroupName: String? = null //当前item对应的Group

        val iTimeIAxis = parent.adapter as ITimeIAxis
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            preGroupName = currentGroupName
            currentGroupName = iTimeIAxis.getGroupName(position)
            if (currentGroupName != preGroupName) {
                val viewBottom = view.bottom
                var top = max(mHeight, view.top).toFloat()
                if (position + 1 < itemCount) {
                    //获取下个GroupName
                    val nextGroupName: String = iTimeIAxis.getGroupName(position + 1)
                    //下一组的第一个View接近头部
                    if (currentGroupName != nextGroupName && viewBottom < top) {
                        top = viewBottom.toFloat()
                    }
                }
                //根据top绘制group
                c.drawRect(marginStart / 4, top - mHeight, marginStart / 4 * 3, top, paint)
                val fm: Paint.FontMetrics = textPaint.fontMetrics
                //文字竖直居中显示
                val baseLine: Float = top - (mHeight - (fm.bottom - fm.top)) / 2 - fm.bottom
                c.drawText(currentGroupName, left.toFloat(), baseLine, textPaint)
            }
        }
    }
}