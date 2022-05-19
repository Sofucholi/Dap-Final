package com.example.feli.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.feli.R
import com.example.feli.feli

class feliAdapter (var datos:List<feli>,var Onclick : (Int) -> Unit,var context: Context): RecyclerView.Adapter<feliAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView
        var itemFachaMeter: ProgressBar
        var itemPhoto:ImageView
        init {
            itemName = itemView.findViewById(R.id.NameTxt)
            itemFachaMeter = itemView.findViewById(R.id.fachaMeter)
            itemPhoto = itemView.findViewById(R.id.MainImageView)

        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {

        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.feli_base, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        var feli = datos[i]
        viewHolder.itemName.text = feli.tipo
        Glide.with(context).load(feli.Profile).centerInside().into(viewHolder.itemPhoto)
        viewHolder.itemFachaMeter.progress = feli.facha
    }
    override fun getItemCount(): Int {
        return datos.size
    }
}