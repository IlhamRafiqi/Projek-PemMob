package com.example.sejenakapps

import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAuth = FirebaseAuth.getInstance()
        webView = findViewById(R.id.webview)

        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebAppInterface(), "Android")

        // 🔹 WebViewClient untuk handle event di halaman HTML
        webView.webViewClient = object : WebViewClient() {

            // Saat halaman selesai dimuat
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                // 🔸 Tombol Tes Kesehatan Mental
                webView.evaluateJavascript(
                    """
                    document.getElementById("btnStartQuiz")?.addEventListener("click", function() {
                        Android.startQuiz();
                    });
                    """.trimIndent(), null
                )

                // 🔸 Tombol Logout
                webView.evaluateJavascript(
                    """
                    document.getElementById("btnLogout")?.addEventListener("click", function() {
                        Android.logout();
                    });
                    """.trimIndent(), null
                )
            }

            // Saat user klik link (misal href yang mengarah ke login.html)
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null && url.contains("login.html")) {
                    // 🔸 Logout dari Firebase
                    firebaseAuth.signOut()

                    // 🔸 Pindah ke halaman LoginActivity
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                    return true // cegah WebView membuka file login.html
                }
                return false
            }
        }

        // 🔹 Muat halaman HTML utama
        webView.loadUrl("file:///android_asset/index.html")
    }

    // Interface untuk panggilan dari JavaScript ke Kotlin
    inner class WebAppInterface {

        @JavascriptInterface
        fun startQuiz() {
            val intent = Intent(this@MainActivity, QuizActivity::class.java)
            startActivity(intent)
        }

        @JavascriptInterface
        fun logout() {
            firebaseAuth.signOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
