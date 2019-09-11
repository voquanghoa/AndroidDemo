package com.quanghoa.appdemo.asset.adapters

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quanghoa.appdemo.R
import com.quanghoa.appdemo.asset.models.Planet
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.planet_layout.*
import org.jetbrains.anko.imageBitmap

class PlanetAdapter(private val planets: List<Planet>) : RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        return PlanetViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.planet_layout, parent, false))
    }

    override fun getItemCount(): Int = planets.size

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        holder.planet = planets[position]
    }

    class PlanetViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer{

        var planet: Planet? = null
            set(value){
                field = value
                txt_name.text = field!!.name
                txt_radius.text = field!!.radius.toString()
                txt_year.text = field!!.year.toString()
                img.imageBitmap = BitmapFactory.decodeStream(containerView.context.assets.open(field!!.image))
            }
    }
}