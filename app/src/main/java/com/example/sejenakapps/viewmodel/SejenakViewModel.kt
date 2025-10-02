package com.example.sejenakapps.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sejenakapps.model.Article
import com.example.sejenakapps.model.Quote
import com.example.sejenakapps.repository.ArticleRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class SejenakViewModel : ViewModel() {

    // Quotes State
    private val _quotes = listOf(
        Quote("Hidup adalah apa yang terjadi saat kita sibuk merencanakan hal lain."),
        Quote("Keberhasilan bukan kunci kebahagiaan. Kebahagiaan adalah kunci keberhasilan."),
        Quote("Jangan menunggu waktu yang tepat, karena waktu tidak pernah datang."),
        Quote("Kebahagiaan tidak tergantung pada apa yang kita miliki, tetapi pada cara kita memandang hidup."),
        Quote("Percayalah pada dirimu sendiri dan semua yang bisa kamu capai.")
    )

    private val _currentQuoteIndex = mutableStateOf(0)
    val currentQuoteIndex: State<Int> = _currentQuoteIndex

    // Articles (from repository)
    private val articleRepository = ArticleRepository()
    private val _articles = mutableStateOf<List<Article>>(emptyList())
    val articles: State<List<Article>> = _articles

    init {
        // Load articles
        _articles.value = articleRepository.getArticles()

        // Auto-change quotes every 5 sec
        viewModelScope.launch {
            while (true) {
                delay(5000)
                _currentQuoteIndex.value = (_currentQuoteIndex.value + 1) % _quotes.size
            }
        }
    }

    fun getCurrentQuote(): Quote {
        return _quotes[_currentQuoteIndex.value]
    }
}
