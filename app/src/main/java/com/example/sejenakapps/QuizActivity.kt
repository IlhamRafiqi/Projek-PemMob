package com.example.sejenakapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private val answers = mutableMapOf<Int, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        webView = findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebAppInterface(), "Android")

        // load quiz.html dari assets
        webView.loadUrl("file:///android_asset/quiz.html")
    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun saveAnswer(questionId: Int, score: Int) {
            answers[questionId] = score
        }

        @JavascriptInterface
        fun submitQuiz() {
            val totalScore = answers.values.sum()
            Log.d("QUIZ", "Total Score = $totalScore")

            val intent = Intent(this@QuizActivity, ResultActivity::class.java)
            intent.putExtra("score", totalScore)
            startActivity(intent)
        }
    }
}
