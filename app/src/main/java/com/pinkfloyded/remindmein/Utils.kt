package com.pinkfloyded.remindmein

import android.util.DisplayMetrics
import android.util.TypedValue

fun toPx(value: Float, fromDim: Int, displayMetrics: DisplayMetrics): Float {
    return TypedValue.applyDimension(fromDim, value, displayMetrics)
}

