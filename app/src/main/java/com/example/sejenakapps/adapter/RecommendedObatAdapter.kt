package com.example.sejenakapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sejenakapps.R
import com.example.sejenakapps.model.ObatModel

class RecommendedObatAdapter(
    private val list: List<ObatModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecommendedObatAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClicked(obat: ObatModel)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoto: ImageView = itemView.findViewById(R.id.imgFoto)
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
        val tvReadMore: TextView = itemView.findViewById(R.id.tvReadMore)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommended_obat, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.imgFoto.setImageResource(item.foto)
        holder.tvNama.text = item.nama
        holder.tvDeskripsi.text = item.deskripsi

        // Klik item atau Read More
        holder.itemView.setOnClickListener { listener.onItemClicked(item) }
        holder.tvReadMore.setOnClickListener { listener.onItemClicked(item) }
    }
}
