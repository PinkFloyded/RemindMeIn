package com.pinkfloyded.remindmein

import android.content.Context
import android.graphics.drawable.GradientDrawable
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable
import kotlin.math.roundToInt

private const val STROKE_WIDTH_DP = 3.5
private const val STROKE_COLOR = R.color.colorAccent

class TimerBackgroundView(context: Context, attrs: AttributeSet?) : FrameLayout(context,
                                                                                attrs,
                                                                                0) {

    constructor(context: Context) : this(context, null)

    private val indeterminateProgressBar: ProgressBar = ProgressBar(context)
    private val circularDrawable: GradientDrawable = GradientDrawable()

    var isRunning: Boolean = false
        set(value) {
            field = value
            refreshBackground()
        }

    init {
        initializeBackgrounds()
        refreshBackground()
    }

    private fun refreshBackground() {
        if (isRunning) {
            indeterminateProgressBar.visibility = View.VISIBLE
            background = null
        } else {
            indeterminateProgressBar.visibility = INVISIBLE
            background = circularDrawable
        }
    }

    private fun initializeBackgrounds() {

        val strokeWidthPx = toPx(STROKE_WIDTH_DP.toFloat(),
                                 TypedValue.COMPLEX_UNIT_DIP,
                                 resources.displayMetrics)
        val strokeColor = ContextCompat.getColor(context, STROKE_COLOR)

        indeterminateProgressBar.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        indeterminateProgressBar.isIndeterminate = true
        indeterminateProgressBar.indeterminateDrawable =
                CircularProgressDrawable.Builder(context)
                    .strokeWidth(strokeWidthPx)
                    .color(strokeColor)
                    .build()
        addView(indeterminateProgressBar)

        circularDrawable.shape = GradientDrawable.OVAL
        circularDrawable.setStroke(strokeWidthPx.roundToInt(), strokeColor)
        background = circularDrawable
    }

}