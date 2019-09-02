package com.quanghoa.appdemo.glideapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.quanghoa.appdemo.R
import kotlinx.android.synthetic.main.glide_fragment_layout.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onEditorAction

class GlideAppFragment : Fragment(){

    var defaultUrl = "https://i.ytimg.com/vi/NqlRg1_bCC4/maxresdefault.jpg"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.glide_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txt_url.setText(defaultUrl)
        txt_url.onEditorAction { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) loadUrl()
        }

        bt_load.onClick { loadUrl() }
        bt_clear.onClick { txt_url.setText("") }

        bt_exam_1.onClick {
            loadUrl("https://raw.githubusercontent.com/bumptech/glide/master/static/glide_logo.png")
        }
        bt_exam_2.onClick {
            loadUrl("https://vnreview.vn/image/14/91/03/1491032.jpg?t=1454000393775")
        }
        bt_exam_3.onClick {
            loadUrl("https://www.pngix.com/pngfile/middle/43-437013_dota-2-team-secret-logo-hd-png-download.png")
        }
    }

    private fun loadUrl(url: String){
        GlideApp.with(this)
            .load(url)
            .placeholder(R.drawable.loading)
            .error(R.drawable.icon_error)
            .transition(withCrossFade())
            .fitCenter()
            .into(imageView)
    }

    private fun loadUrl(){
        loadUrl(txt_url.text.toString())
    }


}