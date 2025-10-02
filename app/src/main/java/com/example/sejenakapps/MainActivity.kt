package com.example.sejenakapps

import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webView = WebView(this)
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/quiz.html")

        setContentView(webView)

        webView = findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true

        webView.addJavascriptInterface(WebAppInterface(), "Android")

        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                // Event listener tombol start quiz
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

        webView.loadUrl("file:///android_asset/index.html")

    }

    inner class WebAppInterface {
        @JavascriptInterface
        fun startQuiz() {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}
