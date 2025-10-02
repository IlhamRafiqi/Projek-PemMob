package com.example.sejenakapps.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sejenakapps.QuizActivity
import com.example.sejenakapps.R
import com.example.sejenakapps.viewmodel.SejenakViewModel
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image


class SejenakActivity : ComponentActivity() {

    private val viewModel: SejenakViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SejenakApp(viewModel)
        }
    }
}

@Composable
fun SejenakApp(viewModel: SejenakViewModel) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            HeroSection()
            Spacer(Modifier.height(16.dp))
            TestSection()
            Spacer(Modifier.height(8.dp))
            BenefitList()
            Spacer(Modifier.height(24.dp))
            QuotesSection(viewModel)
            Spacer(Modifier.height(12.dp))
            ArticlesSection(viewModel)
            Spacer(Modifier.height(24.dp))
            FooterSection()
        }
    }
}

private val BluePrimary = Color(0xFF2757A5)
private val BlueSecondary = Color(0xFF3F7BDF)

@Composable
fun HeroSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(0.dp, 0.dp, 48.dp, 16.dp))
            .background(Brush.verticalGradient(listOf(BluePrimary, BlueSecondary)))
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo",
                    modifier = Modifier.size(64.dp)
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    "Sejenak hadir,\nuntuk pulihkan diri",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Banner",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(120.dp)
            )
        }
    }
}

@Composable
fun TestSection() {
    val context = LocalContext.current

    Column(
        Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Test Kesehatan Mental Kamu, Yuk!", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(4.dp))
        Text("Ikuti tes untuk mendapatkan skor kesehatan mental kamu", fontSize = 13.sp, color = Color(0xFF4F5B70), textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))
        Button(
            onClick = {
                val intent = Intent(context, QuizActivity::class.java)
                context.startActivity(intent)
            },
            shape = CircleShape
        ) {
            Text("Tes Kesehatan Mental Sekarang")
        }
        Spacer(Modifier.height(12.dp))
        Text("Manfaat jika kamu mengikuti tes", fontSize = 14.sp, color = Color(0xFF4F5B70))
    }
}

@Composable
fun BenefitList() {
    Column(Modifier.padding(horizontal = 24.dp)) {
        BenefitItem("Mengetahui kondisi kesehatan mental")
        BenefitItem("Mendapatkan rekomendasi psikolog")
        BenefitItem("Mendapatkan rekomendasi obat")
        BenefitItem("Mengetahui langkah yang tepat")
    }
}

@Composable
fun BenefitItem(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 6.dp)) {
        Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = BluePrimary)
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}

@Composable
fun QuotesSection(viewModel: SejenakViewModel) {
    val quote = viewModel.getCurrentQuote()

    Column(
        Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Quotes Of The Day", color = BluePrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Surface(shape = RoundedCornerShape(16.dp), tonalElevation = 2.dp, modifier = Modifier.fillMaxWidth()) {
            Text(quote.text, textAlign = TextAlign.Center, fontSize = 16.sp, modifier = Modifier.padding(16.dp))
        }
    }
}

@OptIn(androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun ArticlesSection(viewModel: SejenakViewModel) {
    val articles = viewModel.articles.value
    val pagerState = rememberPagerState(pageCount = { articles.size })
    val context = LocalContext.current

    Column(Modifier.fillMaxWidth()) {
        Text("Artikel Terbaru", Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().height(220.dp)) { page ->
            val item = articles[page]
            Surface(
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 4.dp,
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth().clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                    context.startActivity(intent)
                }
            ) {
                Box(Modifier.fillMaxSize().background(Color(0xFF4F5B70))) {
                    Column(Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                        Text(item.title, color = Color.White, fontWeight = FontWeight.Bold)
                        Text(item.date, color = Color.White, fontSize = 12.sp)
                        Text("Read More...", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun FooterSection() {
    Column(
        Modifier.fillMaxWidth().padding(16.dp).clip(RoundedCornerShape(24.dp)).background(Color(0xFFF0F4FF)).padding(16.dp)
    ) {
        Text("Kontak", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Email, contentDescription = null, tint = BluePrimary)
            Spacer(Modifier.width(8.dp))
            Text("contact@website.com")
        }
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Call, contentDescription = null, tint = BluePrimary)
            Spacer(Modifier.width(8.dp))
            Text("081316473969")
        }
        Spacer(Modifier.height(12.dp))
        Text("© Sejenak 2024 | All Rights Reserved", fontSize = 12.sp, color = Color(0xFF4F5B70), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
    }
}
