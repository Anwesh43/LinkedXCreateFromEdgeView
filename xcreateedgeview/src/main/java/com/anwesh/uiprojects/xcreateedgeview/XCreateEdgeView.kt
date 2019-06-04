package com.anwesh.uiprojects.xcreateedgeview

/**
 * Created by anweshmishra on 04/06/19.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Canvas
import android.graphics.Color

val nodes : Int = 5
val lines : Int = 2
val parts : Int = 2
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val strokeFactor : Int = 90
val sizeFactor : Float = 2.9f
val foreColor : Int = Color.parseColor("#0D47A1")
val backColor : Int = Color.parseColor("#BDBDBD")
val rotDeg : Float = 45f

fun Int.inverse() : Float = 1f / this
fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.mirrorValue(a : Int, b : Int) : Float {
    val k : Float = scaleFactor()
    return (1 - k) / a + k / b
}
fun Float.updateValue(dir : Float, a : Int, b : Int) : Float = mirrorValue(a, b) * dir * scGap

fun Canvas.drawXCreateEdge(i : Int, sc1 : Float, sc2 : Float, w  : Float, size : Float, paint : Paint) {
    val sc1i : Float = sc1.divideScale(i, parts)
    val sc2i : Float = sc2.divideScale(i, parts)
    save()
    for (j in 0..(lines - 1)) {
        save()
        translate(w * (1 - sc1i), 0f)
        rotate(rotDeg * sc2i.divideScale(j, lines) * (1f - 2 * j))
        drawLine(0f, 0f, size, 0f, paint)
        restore()
    }
    restore()
}

fun Canvas.drawXCENode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = h / (nodes + 1)
    val size : Float = gap / sizeFactor
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    paint.color = foreColor
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    for (j in 0..(parts - 1)) {
        drawXCreateEdge(j, sc1, sc2, w / 2, size, paint)
    }
}
