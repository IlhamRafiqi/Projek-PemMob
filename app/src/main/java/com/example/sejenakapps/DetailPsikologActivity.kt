package com.example.sejenakapps

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailPsikologActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_psikolog)

        val imgFotoDetail = findViewById<ImageView>(R.id.imgFotoDetail)
        val tvNamaDetail = findViewById<TextView>(R.id.tvNamaDetail)
        val tvLokasiDetail = findViewById<TextView>(R.id.tvLokasiDetail)
        val tvDeskripsiDetail = findViewById<TextView>(R.id.tvDeskripsiDetail)

        // Ambil data dari Intent
        val foto = intent.getIntExtra("foto", 0)
        val nama = intent.getStringExtra("nama")
        val lokasi = intent.getStringExtra("lokasi")
        val deskripsi = intent.getStringExtra("deskripsi")

        // Tampilkan
        imgFotoDetail.setImageResource(foto)
        tvNamaDetail.text = nama
        tvLokasiDetail.text = lokasi
        tvDeskripsiDetail.text = deskripsi
    }
}
