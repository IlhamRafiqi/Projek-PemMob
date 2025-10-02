package com.example.sejenakapps.repository

import com.example.sejenakapps.model.Article

class ArticleRepository {

    // Dummy fetch — nanti bisa diganti API
    fun getArticles(): List<Article> {
        return listOf(
            Article("9 Cara Sederhana Menjaga Kesehatan Mental", "28 Juni, 2024", "https://www.halodoc.com"),
            Article("Mengidap Banyak Gangguan Mental...", "16 Juli, 2024", "https://www.halodoc.com"),
            Article("10 Cara Efektif Mengatasi Masalah Mental", "24 Juli, 2024", "https://www.prudential.co.id")
        )
    }
}
