package com.example.sejenakapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sejenakapps.R
import com.example.sejenakapps.model.PsikologModel

class RecommendedAdapter(
    private val list: List<PsikologModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {

    // Interface untuk callback klik tombol Show
    interface OnItemClickListener {
        fun onShowClicked(psikolog: PsikologModel)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFoto: ImageView = itemView.findViewById(R.id.imgFoto)
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvLokasi: TextView = itemView.findViewById(R.id.tvLokasi)
        val btnShow: Button = itemView.findViewById(R.id.btnShow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommended, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.imgFoto.setImageResource(item.foto)
        holder.tvNama.text = item.nama
        holder.tvLokasi.text = item.lokasi

        // Klik tombol Show
        holder.btnShow.setOnClickListener {
            listener.onShowClicked(item)
        }
    }
}
