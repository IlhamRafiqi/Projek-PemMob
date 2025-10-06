package com.example.sejenakapps.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
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
import com.example.sejenakapps.view.SejenakActivity
import com.example.sejenakapps.view.ArtikelActivity
import com.example.sejenakapps.InformasiActivity


@Composable
fun BottomNavBar(
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    accentBlue: Color = Color(0xFF246BFD)
) {
    val context = LocalContext.current // 🔹 untuk intent

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
        // 🔹 Lapisan bawah — efek kaca
        Box(
            modifier = Modifier
                .matchParentSize()
                .blur(10.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.6f),
                            Color.White.copy(alpha = 0.4f)
                        )
                    )
                )
        )

        // 🔹 Lapisan atas — ikon tetap tajam
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 36.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val items = listOf(
                Icons.Filled.Home,
                Icons.Filled.BookmarkBorder,
                Icons.Filled.FavoriteBorder,
                Icons.Filled.Person
            )

            items.forEachIndexed { index, icon ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        IconButton(
                            onClick = {
                                onItemSelected(index)
                                val intent = when (index) {
                                    0 -> Intent(context, SejenakActivity::class.java)
                                    1 -> Intent(context, ArtikelActivity::class.java)
                                    2 -> Intent(context, InformasiActivity::class.java)
                                    // 3 -> Intent(context, ProfileActivity::class.java)
                                    else -> null
                                }
                                intent?.let { context.startActivity(it) }
                            }
                        )

                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = if (selectedIndex == index)
                                accentBlue
                            else
                                Color(0xFFB4C0CF),
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    if (selectedIndex == index) {
                        Box(
                            modifier = Modifier
                                .width(24.dp)
                                .height(3.dp)
                                .clip(RoundedCornerShape(50))
                                .background(accentBlue)
                        )
                    } else {
                        Spacer(modifier = Modifier.height(3.dp))
                    }
                }
            }
        }
    }
}
