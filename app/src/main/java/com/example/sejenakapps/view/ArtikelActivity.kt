package com.example.sejenak.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sejenakapps.R
import com.example.sejenakapps.ui.BottomNavBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue



// ---------- FontFamily ----------
val Poppins = FontFamily(
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

// ---------- SCREEN ----------
@Composable
fun ArticleScreen() {
    var selectedIndex by remember { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F6FA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 90.dp)
        ) {
            HeaderBar()
            FeaturedArticlesSection()
            LatestNewsSection()
            RecommendationTopicSection()
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 12.dp)
        ) {
            BottomNavBar(
                selectedIndex = selectedIndex,
                onItemSelected = { index ->
                    selectedIndex = index
                }
            )
        }
    }
}


// ---------- HEADER BAR ----------
@Composable
fun HeaderBar(
    modifier: Modifier = Modifier,
    title: String = "Artikel",
    @DrawableRes logoRes: Int = R.drawable.logobiru,
    @DrawableRes profileRes: Int = R.drawable.fotoprofil
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 8.dp), // 🔹 lebih mepet kiri-kanan
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 🔹 Logo kiri (diperbesar)
        Image(
            painter = painterResource(id = logoRes),
            contentDescription = "Logo Sejenak",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(62.dp) // 🔹 dari 52 → 62
        )

        // 🔹 Judul tengah
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color.Black
            )
        }

        // 🔹 Foto profil kanan (diperbesar)
        Image(
            painter = painterResource(id = profileRes),
            contentDescription = "Foto Profil",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(56.dp) // 🔹 dari 46 → 56
                .clip(CircleShape)
                .border(2.dp, Color.LightGray, CircleShape)
        )
    }
}

@Composable
fun FeaturedArticlesSection() {
    val articles = listOf(
        Pair(R.drawable.artikel1, "Cara Menjaga Kesehatan Mental"),
        Pair(R.drawable.artikel2, "Mengenal Gangguan Kecemasan"),
        Pair(R.drawable.artikel3, "Tips Mengelola Stres Harian"),
        Pair(R.drawable.artikel4, "Pentingnya Istirahat yang Cukup"),
        Pair(R.drawable.artikel5, "Menjaga Pola Hidup Sehat")
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        articles.forEach { (imageRes, title) ->
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(240.dp)
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                // 🔹 Gambar utama
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                )

                // 🔹 Overlay gelap lembut di atas gambar
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.25f)) // 🌙 sedikit gelap (0.25 = 25%)
                )

                // 🔹 Gradasi hitam bagian bawah gambar
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f))
                            )
                        )
                        .fillMaxWidth()
                        .height(80.dp)
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )

                // 🔹 Judul artikel
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp, bottom = 32.dp)
                )

                // 🔹 Tulisan "Read more"
                Text(
                    text = "Read more",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp, bottom = 10.dp)
                )
            }
        }
    }
}

@Composable
fun LatestNewsSection() {
    val newsArticles = listOf(
        Pair(R.drawable.artikel6, "5 Rekomendasi destinasi wisata untuk menjaga kesehatan mental"),
        Pair(R.drawable.artikel7, "Menjaga Kesehatan dengan Olahraga")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // 🔹 Header “Latest News”
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Latest News",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                color = Color.Black
            )

            Text(
                text = "View All",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF004AAD) // biru tua
            )
        }

        // 🔹 Konten artikel (scroll horizontal)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(start = 16.dp, top = 12.dp, bottom = 8.dp)
        ) {
            newsArticles.forEach { (imageRes, title) ->
                Column(
                    modifier = Modifier
                        .width(220.dp)
                        .padding(end = 16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(130.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Poppins,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    Text(
                        text = "Nature Channel • 4min ago",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RecommendationTopicSection() {
    val recommendations = listOf(
        Pair(R.drawable.artikel8, "3 Makanan untuk Kesehatan Mental Lebih Baik"),
        Pair(R.drawable.artikel9, "Hindari Lingkungan Toxic !")
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // 🔹 Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recommendation Topic",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                color = Color.Black
            )

            Text(
                text = "View All",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF004AAD) // biru tua
            )
        }

        // 🔹 Daftar rekomendasi (vertikal)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            recommendations.forEach { (imageRes, title) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 12.dp)
                    ) {
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Poppins,
                            color = Color.Black
                        )

                        Text(
                            text = "Nature Channel  •  4min ago",
                            fontSize = 12.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }
    }
}

