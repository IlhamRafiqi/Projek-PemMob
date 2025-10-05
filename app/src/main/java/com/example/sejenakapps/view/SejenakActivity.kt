package com.example.sejenakapps.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sejenakapps.R
import com.example.sejenakapps.viewmodel.SejenakViewModel
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.SideEffect
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.SideEffect
import com.example.sejenak.view.ArticleScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class SejenakActivity : ComponentActivity() {
    private val viewModel: SejenakViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Biarkan Compose menggambar seluruh area layar
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Hapus semua background window default
        window.setBackgroundDrawable(null)
        window.navigationBarColor = android.graphics.Color.TRANSPARENT
        window.statusBarColor = android.graphics.Color.parseColor("#246BFD") // atas biru

        // Pastikan sistem tidak memaksa warna hitam di bawah
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        setContent {
            val systemUiController = rememberSystemUiController()

            SideEffect {
                // 🔹 Atas mengikuti hero section (biru)
                systemUiController.setStatusBarColor(
                    color = Color(0xFF246BFD),
                    darkIcons = false
                )
                // 🔹 Bawah full transparan
                systemUiController.setNavigationBarColor(
                    color = Color.Transparent,
                    darkIcons = true
                )
            }

            // 🔹 Bungkus seluruh konten
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                ArticleScreen()
            //SejenakApp(viewModel)
            }
        }
    }

}

/* ---------- FONT Poppins (pastikan file ada di res/font/) ---------- */
val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

/* ---------- App Entry ---------- */
@Composable
fun SejenakApp(viewModel: SejenakViewModel) {
    val accentBlue = Color(0xFF246BFD)

    Scaffold(
        bottomBar = {
            Box(modifier = Modifier.background(Color.Transparent)) {
                BottomNav(accentBlue)
            }
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.White) // pertahankan, tapi ini hanya untuk konten scroll
                .verticalScroll(rememberScrollState())
        ) {
            HeroSection(accentBlue)
            Spacer(modifier = Modifier.height(28.dp))
            TestSection(accentBlue)
            Spacer(modifier = Modifier.height(24.dp))
            BenefitSection(accentBlue)
            Spacer(modifier = Modifier.height(24.dp))
            QuoteSection()
            Spacer(modifier = Modifier.height(24.dp))
            ArticleSection(accentBlue)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

/* ---------- HERO / HEADER ---------- */
@SuppressLint("Range")
@Composable
fun HeroSection(accentBlue: Color) {
    val deepBlue = Color(0xFF1E4EB2)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(bottomStart = 90.dp, bottomEnd = 90.dp),
                clip = false
            )
            .background(
                color = deepBlue,
                shape = RoundedCornerShape(bottomStart = 90.dp, bottomEnd = 90.dp)
            )
    ) {
        // 🔹 Gambar latar belakang — diperbesar lagi 1 kali lipat dan diturunkan
        Image(
            painter = painterResource(id = R.drawable.gambarberanda4),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(3.0f) // sebelumnya 2.5f → sekarang lebih besar
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = 2.4f, // sebelumnya 1.8f → sekarang 2.4f (lebih besar 1 kali lipat)
                    scaleY = 2.4f,
                    translationY = 200f // sebelumnya 70f → diturunkan lagi agar pas di tengah tulisan
                )
                .alpha(0.25f),
            contentScale = ContentScale.Fit
        )

        // 🔹 Konten utama (logo + teks)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            // 🔹 Logo dan teks atas
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_sejenak),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp) // logo tetap proporsional
                        .padding(end = 10.dp)
                )
                Column {
                    Text(
                        text = "Sejenak Hadir,",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Untuk Pulihkan diri !",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(35.dp))

            // 🔹 Garis dan teks "Welcome"
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(4.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Welcome to Sejenak",
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.White
                )
            }
        }
    }
}

/* ---------- TEST SECTION (judul besar + tombol) ---------- */
@Composable
private fun TestSection(accentBlue: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔹 Judul dipisah jadi dua baris agar bisa kontrol jarak antarbaris
        Text(
            text = "Tes Kesehatan Mental",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = accentBlue,
            textAlign = TextAlign.Center
        )
        Text(
            text = "kamu, Yuk!",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = accentBlue,
            textAlign = TextAlign.Center,
            modifier = Modifier.offset(y = (-10).dp) // 🔸 Rapatkan dua baris
        )

        // 🔹 Jarak antar judul dan deskripsi
        Spacer(modifier = Modifier.height(30.dp))

        // 🔹 Deskripsi
        Text(
            text = "Ikuti tes untuk mendapatkan skor kesehatan mental kamu",
            fontFamily = Poppins,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Color(0xFF6B6B6B),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 8.dp)
        )

        // 🔹 Jarak antar deskripsi dan tombol
        Spacer(modifier = Modifier.height(40.dp))

        // 🔹 Tombol besar pill dengan shadow
        Box(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(0.7f)
                .shadow(10.dp, RoundedCornerShape(40.dp))
                .clip(RoundedCornerShape(40.dp))
                .background(accentBlue)
                .clickable { /* TODO: navigasi ke quiz */ },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Tes Kesehatan Mental\nSekarang !",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            )
        }

        // 🔹 Tambahkan jarak bawah untuk transisi ke section berikut
        Spacer(modifier = Modifier.height( 3.dp))
    }
}

/* ---------- BENEFIT / MANFAAT ---------- */
@Composable
private fun BenefitSection(accentBlue: Color) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp), // 🔹 hilangkan padding horizontal agar center beneran
        horizontalAlignment = Alignment.CenterHorizontally // 🔹 semua isi di tengah
    ) {
        // 🔹 Judul di tengah layar
        Text(
            text = "Manfaat jika kamu mengikuti tes",
            fontFamily = Poppins,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.5.sp,
            textAlign = TextAlign.Center,
            color = accentBlue,
            modifier = Modifier.fillMaxWidth(0.9f) // 🔹 sedikit margin agar tidak mentok layar
        )

        Spacer(modifier = Modifier.height(16.dp))

        val items = listOf(
            "Mengetahui kondisi kesehatan mental kamu secara umum",
            "Mendapatkan rekomendasi psikolog sesuai kebutuhanmu",
            "Mendapatkan saran atau rujukan obat yang relevan",
            "Mengetahui langkah terbaik untuk pemulihan"
        )

        // 🔹 List manfaat dengan ikon
        Column(
            modifier = Modifier.fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items.forEach { item ->
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = accentBlue,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = item,
                        fontFamily = Poppins,
                        fontSize = 15.sp,
                        lineHeight = 18.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@Composable
private fun QuoteSection() {
    val accentBlue = Color(0xFF1E4EB2) // Warna biru utama aplikasi kamu

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(horizontal = 28.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        // Latar belakang putih agar kontras dengan border hitam
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.White)
        )

        // Gambar pinggiran PNG (gambarberanda2)
        Image(
            painter = painterResource(id = R.drawable.gambarberanda2),
            contentDescription = null,
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer(
                    scaleX = 1.1f,
                    scaleY = 1.1f
                ),
            colorFilter = ColorFilter.tint(
                Color.Black, // ubah warna pinggir menjadi hitam
                blendMode = BlendMode.SrcAtop
            ),
            contentScale = ContentScale.Fit
        )

        // Isi teks di tengah
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Judul
            Text(
                text = "Quotes Of The Day",
                color = accentBlue, // Warna biru
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Isi kutipan
            Text(
                text = "Kita tidak bisa mengubah arah angin,\n" +
                        "tetapi kita bisa menyesuaikan layar.",
                color = accentBlue, // Warna biru
                fontFamily = Poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ArticleSection(accentBlue: Color) {
    // Daftar artikel + gambar
    val articles = listOf(
        Pair("Cara Menjaga Kesehatan Mental", R.drawable.artikel1),
        Pair("Mengenal Gangguan Kecemasan", R.drawable.artikel2),
        Pair("Tips Mengelola Stres Harian", R.drawable.artikel3),
        Pair("Pentingnya Istirahat Bagi Kesehatan Mental", R.drawable.artikel4),
        Pair("Menemukan Dukungan Emosional", R.drawable.artikel5)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        // Judul utama
        Text(
            text = "Artikel Terbaru",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp, // dua kali lebih besar
            color = Color(0xFF0D47A1), // biru tua
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        // Baris artikel yang dapat digeser
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            articles.forEach { (title, imageRes) ->
                Card(
                    modifier = Modifier
                        .width(260.dp)
                        .height(310.dp)
                        .padding(end = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // 🔹 Gambar di bagian atas
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(190.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        )

                        // 🔹 Teks di bawah gambar
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = title,
                                fontFamily = Poppins,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = Color.Black,
                                lineHeight = 22.sp
                            )
                            Text(
                                text = "Read more",
                                fontFamily = Poppins,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp,
                                color = accentBlue
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun BottomNav(accentBlue: Color) {
    var selected by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(bottom = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(75.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(50.dp),
                    clip = false
                )
                .graphicsLayer { alpha = 0.9f }, // efek transparan halus
            color = Color.White.copy(alpha = 0.6f), // lebih transparan, bukan solid
            shape = RoundedCornerShape(50.dp),
            tonalElevation = 0.dp
        ) {
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
                    IconButton(onClick = { selected = index }) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = if (selected == index)
                                accentBlue
                            else
                                Color.Gray.copy(alpha = 0.6f),
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomIconFlat(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    accent: Color,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(60.dp)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) accent else Color(0xFFB4C0CF),
            modifier = Modifier.size(28.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(26.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(accent)
            )
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
