# ChartSampleForKotlin
Pie chart with rounded start and end points - Kotlin

```
package com.example.chartsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

class ChartView: View {
    var rate:Float = 0f
    var isNotClockWise:Boolean = false

    constructor(context: Context) : super(context, null)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBaseChart(canvas)
        drawValueChart(canvas)
    }

    private fun drawBaseChart(canvas:Canvas?){
        val paint = Paint()
        paint.setColor(Color.rgb(255,212,121))
        val storkeWidth:Float = (getWidth() / 8).toFloat()
        paint.setStrokeWidth(storkeWidth)
        paint.setAntiAlias(true)
        paint.setStyle(Paint.Style.STROKE)
        paint.strokeCap = Paint.Cap.ROUND
        val rect = RectF(storkeWidth/2, storkeWidth/2, getWidth().toFloat()-storkeWidth/2, getHeight().toFloat()-storkeWidth/2)
        canvas!!.drawArc(rect, 0f, 360f, false, paint)
    }

    private fun drawValueChart(canvas:Canvas?){
        val paint = Paint()
        paint.setColor(Color.rgb(255,138,216))
        val storkeWidth:Float = (getWidth() / 8).toFloat()
        paint.setStrokeWidth(storkeWidth)
        paint.setAntiAlias(true)
        paint.setStyle(Paint.Style.STROKE)
        paint.strokeCap = Paint.Cap.ROUND
        val rect = RectF(storkeWidth/2, storkeWidth/2, getWidth().toFloat()-storkeWidth/2, getHeight().toFloat()-storkeWidth/2)
        var angle = rate / 100 * 360
        if (isNotClockWise) {
            angle *= -1
        }
        canvas!!.drawArc(rect, -90f, angle, false, paint)
    }
}
```
