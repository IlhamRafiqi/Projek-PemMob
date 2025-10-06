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
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext




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
    val context = LocalContext.current

    val articles = listOf(
        Triple(
            R.drawable.artikel1,
            "Cara Menjaga Kesehatan Mental",
            "https://share.google/gmLmVUs8SfZ5qdvr2"
        ),
        Triple(
            R.drawable.artikel2,
            "Mengenal Gangguan Kecemasan",
            "https://www.alodokter.com/kenali-tiga-jenis-gangguan-kecemasan-dan-gejalanya"
        ),
        Triple(
            R.drawable.artikel3,
            "Tips Mengelola Stres Harian",
            "https://www.alodokter.com/ternyata-tidak-sulit-mengatasi-stres"
        ),
        Triple(
            R.drawable.artikel4,
            "Pentingnya Istirahat yang Cukup",
            "https://ners.unair.ac.id/site/index.php/news-fkp-unair/30-lihat/1047-pentingnya-istirahat-yang-cukup"
        ),
        Triple(
            R.drawable.artikel5,
            "Menjaga Pola Hidup Sehat",
            "https://www.alodokter.com/delapan-langkah-menuju-pola-hidup-sehat"
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        articles.forEach { (imageRes, title, url) ->
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

                // 🔹 Overlay gelap lembut
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.25f))
                )

                // 🔹 Gradasi hitam bawah
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

                // 🔹 Tombol "Read more" — klik buka link
                Text(
                    text = "Read more",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 16.dp, bottom = 10.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
                )
            }
        }
    }
}


@Composable
fun LatestNewsSection() {
    val context = LocalContext.current

    val newsArticles = listOf(
        Triple(
            R.drawable.artikel6,
            "5 Rekomendasi destinasi wisata untuk menjaga kesehatan mental",
            "https://www.ladiestory.id/5-wisata-lokal-yang-baik-untuk-menjaga-kesehatan-mental-55723"
        ),
        Triple(
            R.drawable.artikel7,
            "Menjaga Kesehatan dengan Olahraga",
            "https://www.alodokter.com/beragam-manfaat-olahraga"
        )
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
                color = Color(0xFF004AAD)
            )
        }

        // 🔹 Konten artikel (scroll horizontal)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(start = 16.dp, top = 12.dp, bottom = 8.dp)
        ) {
            newsArticles.forEach { (imageRes, title, url) ->
                Column(
                    modifier = Modifier
                        .width(220.dp)
                        .padding(end = 16.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }
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
    val context = LocalContext.current

    val recommendations = listOf(
        Triple(
            R.drawable.artikel8,
            "3 Makanan untuk Kesehatan Mental Lebih Baik",
            "https://hellosehat.com/mental/stres/makanan-untuk-kesehatan-jiwa/"
        ),
        Triple(
            R.drawable.artikel9,
            "Hindari Lingkungan Toxic !",
            "https://alive.generali.co.id/blog/detail/menghindari-lingkungan-yang-toxic"
        )
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
                color = Color(0xFF004AAD)
            )
        }

        // 🔹 Daftar rekomendasi (vertikal)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            recommendations.forEach { (imageRes, title, url) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        },
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


