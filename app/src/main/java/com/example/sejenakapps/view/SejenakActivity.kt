package com.example.sejenakapps.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.sejenak.view.ArticleScreen
import com.example.sejenakapps.R
import com.example.sejenakapps.ui.BottomNavBar
import com.example.sejenakapps.viewmodel.SejenakViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.sejenakapps.ui.BottomNavBar
import android.content.Intent
import com.example.sejenakapps.QuizActivity
import androidx.compose.ui.platform.LocalContext


class SejenakActivity : ComponentActivity() {
    private val viewModel: SejenakViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SejenakApp(viewModel)
            //ArticleScreen()
        }
    }
}

/* ---------- FONT ---------- */
val Poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

/* ---------- APP ---------- */
@Composable
fun SejenakApp(viewModel: SejenakViewModel) {
    var selectedIndex by remember { mutableStateOf(0) }
    val accentBlue = Color(0xFF246BFD)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F6FA))
    ) {
        // Konten utama halaman
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 32.dp, start = 16.dp, end = 16.dp, bottom = 90.dp)
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
            ContactImageCard()
            Spacer(modifier = Modifier.height(24.dp))
        }

        // NAVBAR dari BottomNavbar.kt
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

/* ---------- HERO ---------- */
@SuppressLint("Range")
@Composable
fun HeroSection(accentBlue: Color) {
    val deepBlue = Color(0xFF1E4EB2)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .shadow(12.dp, RoundedCornerShape(bottomStart = 90.dp, bottomEnd = 90.dp))
            .background(deepBlue, RoundedCornerShape(bottomStart = 90.dp, bottomEnd = 90.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.gambarberanda4),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(3.0f)
                .align(Alignment.Center)
                .graphicsLayer(scaleX = 2.4f, scaleY = 2.4f, translationY = 200f)
                .alpha(0.25f),
            contentScale = ContentScale.Fit
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logo_sejenak),
                    contentDescription = null,
                    modifier = Modifier.size(90.dp).padding(end = 10.dp)
                )
                Column {
                    Text("Sejenak Hadir,", fontFamily = Poppins, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Color.White)
                    Text("Untuk Pulihkan diri !", fontFamily = Poppins, fontSize = 16.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(35.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.width(70.dp).height(4.dp).background(Color.White, RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Welcome to Sejenak", fontFamily = Poppins, fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color.White)
            }
        }
    }
}

/* ---------- TEST SECTION ---------- */
@Composable
private fun TestSection(accentBlue: Color) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 28.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Tes Kesehatan Mental",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = accentBlue
        )
        Text(
            "kamu, Yuk!",
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = accentBlue,
            modifier = Modifier.offset(y = (-10).dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            "Ikuti tes untuk mendapatkan skor kesehatan mental kamu",
            fontFamily = Poppins,
            fontSize = 16.sp,
            color = Color(0xFF6B6B6B),
            textAlign = TextAlign.Center,
            lineHeight = 22.sp
        )
        Spacer(modifier = Modifier.height(40.dp))

        // 🔹 Tombol menuju QuizActivity
        Box(
            modifier = Modifier
                .height(64.dp)
                .fillMaxWidth(0.7f)
                .shadow(10.dp, RoundedCornerShape(40.dp))
                .clip(RoundedCornerShape(40.dp))
                .background(accentBlue)
                .clickable {
                    val intent = Intent(context, QuizActivity::class.java)
                    context.startActivity(intent)
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Tes Kesehatan Mental\nSekarang !",
                fontFamily = Poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(3.dp))
    }
}


/* ---------- BENEFIT SECTION ---------- */
@Composable
private fun BenefitSection(accentBlue: Color) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Manfaat jika kamu mengikuti tes",
            fontFamily = Poppins, fontWeight = FontWeight.SemiBold, fontSize = 20.5.sp,
            textAlign = TextAlign.Center, color = accentBlue
        )
        Spacer(modifier = Modifier.height(16.dp))

        val items = listOf(
            "Mengetahui kondisi kesehatan mental kamu secara umum",
            "Mendapatkan rekomendasi psikolog sesuai kebutuhanmu",
            "Mendapatkan saran atau rujukan obat yang relevan",
            "Mengetahui langkah terbaik untuk pemulihan"
        )

        Column(modifier = Modifier.fillMaxWidth(0.9f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            items.forEach {
                Row(verticalAlignment = Alignment.Top, modifier = Modifier.fillMaxWidth()) {
                    Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = accentBlue, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(it, fontFamily = Poppins, fontSize = 15.sp, color = Color.Black)
                }
            }
        }
    }
}

/* ---------- QUOTE SECTION ---------- */
@Composable
private fun QuoteSection() {
    val accentBlue = Color(0xFF1E4EB2)
    Box(
        modifier = Modifier.fillMaxWidth().height(220.dp).padding(horizontal = 28.dp).clip(RoundedCornerShape(16.dp))
    ) {
        Box(modifier = Modifier.matchParentSize().background(Color.White))
        Image(
            painter = painterResource(id = R.drawable.gambarberanda2),
            contentDescription = null,
            modifier = Modifier.matchParentSize().graphicsLayer(scaleX = 1.1f, scaleY = 1.1f),
            colorFilter = ColorFilter.tint(Color.Black, blendMode = BlendMode.SrcAtop),
            contentScale = ContentScale.Fit
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp).padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Quotes Of The Day", color = accentBlue, fontFamily = Poppins, fontWeight = FontWeight.SemiBold, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Kita tidak bisa mengubah arah angin,\n tetapi kita bisa menyesuaikan layar.",
                color = accentBlue, fontFamily = Poppins, fontSize = 15.sp, textAlign = TextAlign.Center
            )
        }
    }
}

/* ---------- ARTICLE SECTION ---------- */
@Composable
fun ArticleSection(accentBlue: Color) {
    val articles = listOf(
        Pair("Cara Menjaga Kesehatan Mental", R.drawable.artikel1),
        Pair("Mengenal Gangguan Kecemasan", R.drawable.artikel2),
        Pair("Tips Mengelola Stres Harian", R.drawable.artikel3),
        Pair("Pentingnya Istirahat Bagi Kesehatan Mental", R.drawable.artikel4),
        Pair("Menemukan Dukungan Emosional", R.drawable.artikel5)
    )

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
    ) {
        Text(
            "Artikel Terbaru", fontFamily = Poppins, fontWeight = FontWeight.Bold,
            fontSize = 35.sp, color = Color(0xFF0D47A1), textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp)
        )

        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            articles.forEach { (title, imageRes) ->
                Card(
                    modifier = Modifier.width(260.dp).height(310.dp).padding(end = 16.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.height(190.dp).fillMaxWidth().clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        )
                        Column(
                            modifier = Modifier.padding(16.dp).fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(title, fontFamily = Poppins, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Color.Black)
                            Text("Read more", fontFamily = Poppins, fontWeight = FontWeight.Medium, fontSize = 14.sp, color = accentBlue)
                        }
                    }
                }
            }
        }
    }
}

/* ---------- CONTACT IMAGE ---------- */
@Composable
fun ContactImageCard(onClick: () -> Unit = {}) {
    Image(
        painter = painterResource(id = R.drawable.kontak1),
        contentDescription = "Kartu Kontak Sejenak",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth().height(180.dp).padding(horizontal = 20.dp, vertical = 16.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
    )
}