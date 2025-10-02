package com.example.sejenakapps

import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)  // ✅ gunakan properti class
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebAppInterface(), "Android")

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                // inject JS listener untuk tombol di html
                webView.evaluateJavascript(
                    """
                    document.getElementById("btnStartQuiz")?.addEventListener("click", function() {
                        Android.startQuiz();
                    });
                    """.trimIndent(),
                    null
                )
            }
        }

        // load halaman awal (pilih salah satu: index.html / quiz.html)
        webView.loadUrl("file:///android_asset/index.html")

        setContentView(webView)
    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun startQuiz() {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}
