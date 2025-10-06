package com.example.sejenakapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sejenakapps.view.TentangScreen  // ✅ ubah import

class TentangActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TentangScreen()  // ✅ ubah fungsi yang dipanggil
        }
    }
}
