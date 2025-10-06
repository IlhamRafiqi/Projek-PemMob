package com.example.sejenakapps.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.sejenakapps.MainActivity
import com.example.sejenakapps.ArtikelActivity
import com.example.sejenakapps.TentangActivity
import com.example.sejenakapps.InformasiActivity


@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    accentColor: Color = Color(0xFF0ABAB5) // Warna toska lembut
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(75.dp)
            .shadow(
                elevation = 15.dp,
                shape = RoundedCornerShape(50.dp),
                clip = false
            )
            .clip(RoundedCornerShape(50.dp))
    ) {
        // 🔹 Latar belakang kaca transparan
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(10.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.7f),
                            Color.White.copy(alpha = 0.4f)
                        )
                    )
                )
        )

        // 🔹 Deretan ikon navigasi
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 36.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Urutan ikon: Home, Artikel, Informasi, Tentang Kami
            val items = listOf(
                Icons.Filled.Home,        // index 0 - Sejenak (Home)
                Icons.Filled.Bookmark,    // index 1 - Artikel
                Icons.Filled.Favorite,    // index 2 - Informasi
                Icons.Filled.Person       // index 3 - Tentang Kami
            )

            items.forEachIndexed { index, icon ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            onItemSelected(index)

                            // 🔹 Navigasi Activity
                            when (index) {
                                0 -> {
                                    val intent = Intent(context, MainActivity::class.java)
                                    context.startActivity(intent)
                                }
                                1 -> {
                                    val intent = Intent(context, ArtikelActivity::class.java)
                                    context.startActivity(intent)
                                }
                                2 -> {
                                    val intent = Intent(context, InformasiActivity::class.java)
                                    context.startActivity(intent)
                                }
                                3 -> {
                                    val intent = Intent(context, TentangActivity::class.java)
                                    context.startActivity(intent)
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = if (selectedIndex == index)
                                accentColor
                            else
                                Color(0xFFB4C0CF),
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    // 🔹 Garis indikator bawah ikon aktif
                    if (selectedIndex == index) {
                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .height(3.dp)
                                .clip(RoundedCornerShape(50))
                                .background(accentColor)
                        )
                    } else {
                        Spacer(modifier = Modifier.height(3.dp))
                    }
                }
            }
        }
    }
}
