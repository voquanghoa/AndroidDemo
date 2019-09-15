package com.quanghoa.appdemo.asset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.quanghoa.appdemo.R
import kotlinx.android.synthetic.main.asset_fragment_layout.*
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.quanghoa.appdemo.asset.adapters.PlanetAdapter
import com.quanghoa.appdemo.asset.models.Data


class AssetFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.asset_fragment_layout, container, false)
    }

    private fun readText(path: String): String{
        return context!!.assets.open(path).use {
            String(it.readBytes())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt1.text = readText("data.txt")

        recycler_view.addItemDecoration(
            DividerItemDecoration(
                context!!,
                DividerItemDecoration.VERTICAL
            )
        )

        val data = Gson().fromJson(readText("data.json"), Data::class.java)
        recycler_view.adapter = PlanetAdapter(data.planets)
    }
}