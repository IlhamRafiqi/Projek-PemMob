package com.example.sejenakapps

import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)

        webView = findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebAppInterface(), "Android")

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                // Update speedometer JS
                webView.evaluateJavascript("updateSpeedometer($score)", null)
            }
        }

        webView.loadUrl("file:///android_asset/result.html")
    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun restartQuiz() {
            val intent = Intent(this@ResultActivity, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}
