package com.quanghoa.appdemo.composite.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.quanghoa.appdemo.R
import kotlinx.android.synthetic.main.rating_view_layout.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class RatingView(context: Context, attrs: AttributeSet?)
    : ConstraintLayout(context, attrs){

    private var ratingButtons: List<ImageButton>

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.rating_view_layout, this, true)

        ratingButtons = container.children
            .map { it as ImageButton }
            .toList()

        ratingButtons.forEachIndexed{
                i, b -> b.onClick { star = i + 1 }
        }

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.RatingView)
            star = typedArray.getInteger(R.styleable.RatingView_star, star)
            typedArray.recycle()
        }
    }

    var star: Int = 0
        set(value){
            if(value in 0..5){
                field = value
                ratingButtons.forEachIndexed{
                        i, b -> b.isActivated = i < value
                }
            }
        }
}