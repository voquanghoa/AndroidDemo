package com.quanghoa.appdemo.support.multilanguages

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quanghoa.appdemo.R
import kotlinx.android.synthetic.main.multi_language_layout.*

class MultiLanguagesFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.multi_language_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(LocaleHelper.getLanguage(context!!)){
            "en" -> rb_english.isChecked = true
            "vi" -> rb_vietnamese.isChecked = true
        }

        rg_languages.setOnCheckedChangeListener { _, _ ->
            val language = if (rb_english.isChecked) "en" else "vi"
            LocaleHelper.setLocale(context!!, language)
            (context as Activity).recreate()
        }
    }
}