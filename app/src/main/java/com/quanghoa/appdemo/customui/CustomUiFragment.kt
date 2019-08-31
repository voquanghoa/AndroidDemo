package com.quanghoa.appdemo.customui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quanghoa.appdemo.R
import kotlinx.android.synthetic.main.custom_ui_layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class CustomUiFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.custom_ui_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_increase.onClick {
            circleChart.progress ++
        }

        bt_decrease.onClick {
            circleChart.progress --
        }
    }
}