package com.quanghoa.appdemo.customui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.quanghoa.appdemo.R
import kotlin.math.min


class CircleChart(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context): this(context, null, 0, 0)
    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): this(context, attrs, defStyleAttr, 0)

    var progress: Int = 40
        set(value){
            if(value != field && value in 0..100){
                field = value
                invalidate()
            }
        }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var fullRect = RectF()
    private var textBound = Rect()
    private var strokeWidth = 20f
    private var textSize = 60f

    private var backColor = ContextCompat.getColor(context, R.color.colorPrimary)
    private var progressColor = ContextCompat.getColor(context, R.color.colorAccent)
    private var textColor = ContextCompat.getColor(context, R.color.colorAccent)

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CircleChart)

            progress = typedArray.getInteger(R.styleable.CircleChart_progress, progress)
            strokeWidth = typedArray.getFloat(R.styleable.CircleChart_progressWidth, strokeWidth)
            textSize = typedArray.getDimension(R.styleable.CircleChart_textSize, strokeWidth)
            backColor = typedArray.getColor(R.styleable.CircleChart_backColor, backColor)
            progressColor = typedArray.getColor(R.styleable.CircleChart_progressColor, progressColor)
            textColor = typedArray.getColor(R.styleable.CircleChart_textColor, textColor)

            typedArray.recycle()
        }

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth

        textPaint.textSize = textSize
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.color = textColor

        fullRect.left = strokeWidth
        fullRect.top = strokeWidth
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        fullRect.bottom = h - strokeWidth
        fullRect.right = w - strokeWidth
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = backColor
        canvas.drawArc(fullRect, 270f, 360f,false, paint)

        paint.color = progressColor
        canvas.drawArc(fullRect, 270f, (progress * 360.0 / 100).toFloat(),false, paint)

        val text = "$progress%"
        textPaint.getTextBounds(text, 0, text.length, textBound)
        canvas.drawText(text, fullRect.centerX(), fullRect.centerY() - textBound.centerY(), textPaint)
    }
}