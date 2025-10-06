package com.example.sejenakapps.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sejenakapps.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.clip
import com.example.sejenakapps.ui.BottomNavBar

fun Modifier.Companion.align(bottomCenter: Alignment): Any {
    return TODO("Provide the return value")
}

@Composable
fun TentangScreen() {
    // 🔹 Semua konten bisa di-scroll
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderTentang()
        Spacer(modifier = Modifier.height(30.dp))
        LogoSection()
        InfoCardSection()
        TentangImageSection()
        Spacer(modifier = Modifier.height(60.dp))
    }

}


/* ---------- FONT ---------- */
val poppins = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)



/* ---------- HEADER (SAMA DENGAN HERO SECTION DI SEJENAKACTIVITY) ---------- */
@SuppressLint("Range")
@Composable
fun HeaderTentang() {
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
        // 🔹 Gambar latar belakang
        Image(
            painter = painterResource(id = R.drawable.gambarberanda4),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(3.0f)
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = 2.4f,
                    scaleY = 2.4f,
                    translationY = 200f
                )
                .alpha(0.25f),
            contentScale = ContentScale.Fit
        )

        // 🔹 Konten teks dan logo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 8.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_sejenak),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
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

@Composable
fun LogoSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        Color(0xFFD9E8FF),
                        Color.White
                    ),
                    startY = 0f,
                    endY = 600f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logobiru),
                contentDescription = "Logo Sejenak",
                modifier = Modifier
                    .size(300.dp)
            )
        }
    }
}

@Composable
fun InfoCardSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .shadow(8.dp, RoundedCornerShape(24.dp))
            .background(Color.White, RoundedCornerShape(24.dp))
            .padding(20.dp)
    ) {
        Column {
            // 🔹 Bagian atas (logo + nama)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.logobiru),
                    contentDescription = "Logo Sejenak",
                    modifier = Modifier
                        .size(42.dp)
                        .padding(end = 10.dp)
                )
                Column {
                    Text(
                        text = "Sejenak",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Text(
                        text = "Sejenak Inc.",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "⋮",
                    fontSize = 24.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Teks utama
            Text(
                text = "Sejenak hadir, Untuk Pulihkan Diri !",
                fontSize = 16.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 🔹 Ikon love & kirim
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Like",
                        tint = Color(0xFFFF6B6B),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("99k+", color = Color.Gray)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Share",
                        tint = Color(0xFF246BFD),
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("99k+", color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun TentangImageSection() {
    Image(
        painter = painterResource(id = R.drawable.tentang),
        contentDescription = "Tentang Kami",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(800.dp)
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
    )
}
