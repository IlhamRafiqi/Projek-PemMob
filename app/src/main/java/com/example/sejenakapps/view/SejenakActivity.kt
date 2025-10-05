package com.example.sejenakapps.view

import android.annotation.SuppressLint
import android.os.Bundle
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

class SejenakActivity : ComponentActivity() {
    private val viewModel: SejenakViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SejenakApp(viewModel)
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
        // kita pakai custom bottom bar (di bawah)
        bottomBar = { BottomNav(accentBlue) },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            HeroSection(accentBlue)
            Spacer(modifier = Modifier.height(28.dp))
            TestSection(accentBlue)
            Spacer(modifier = Modifier.height(24.dp))
            BenefitSection(accentBlue)
            Spacer(modifier = Modifier.height(24.dp))
            QuoteSection()
            Spacer(modifier = Modifier.height(120.dp)) // beri ruang sebelum bottom nav
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
private fun BottomNav(accentBlue: Color) {
    var selected by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp) // jarak dari bawah
            .background(Color.Transparent), // pastikan transparan
        contentAlignment = Alignment.Center
    ) {
        // Card utama (melengkung + shadow)
        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(70.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(50.dp),
                    ambientColor = Color.Black.copy(alpha = 0.25f),
                    spotColor = Color.Black.copy(alpha = 0.25f)
                ),
            shape = RoundedCornerShape(50.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White.copy(alpha = 0.55f) // semi transparan (hilangkan putih padat)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val items = listOf(
                    Icons.Filled.Home to "Home",
                    Icons.Filled.Book to "Artikel",
                    Icons.Filled.Favorite to "Favorit",
                    Icons.Filled.Person to "Profil"
                )

                items.forEachIndexed { index, (icon, label) ->
                    BottomIcon(
                        icon = icon,
                        label = label,
                        isSelected = selected == index,
                        accent = accentBlue
                    ) {
                        selected = index
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomIcon(
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
            .width(65.dp)
            .clickable { onClick() }
    ) {
        // Ikon dengan latar bulat halus jika aktif
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(if (isSelected) accent.copy(alpha = 0.15f) else Color.Transparent)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) accent else Color(0xFFB0B8C1),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Garis biru kecil di bawah ikon aktif
        if (isSelected) {
            Box(
                modifier = Modifier
                    .width(22.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(accent)
            )
        } else {
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}