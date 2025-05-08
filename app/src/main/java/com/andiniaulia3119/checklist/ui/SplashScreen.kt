package com.andiniaulia3119.checklist.ui

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

class SplashScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SplashScreen(navController = navController)
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    // Menyembunyikan splash screen setelah beberapa detik
    var isVisible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000) // Tampilkan splash selama 2 detik
        isVisible = false // Splash screen mulai fade out setelah 2 detik
        delay(500) // Tunggu sejenak sebelum navigasi
        navController.navigate("itemList") // Navigasi ke ItemListScreen
    }

    // Menampilkan konten splash dengan efek fade-in dan fade-out
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AnimatedVisibility(
                visible = isVisible,
                enter = fadeIn(), // Efek fade-in saat splash muncul
                exit = fadeOut() // Efek fade-out setelah 2 detik
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(R.drawable.ceklis_logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(128.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Selamat datang di DapurKu!")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun SplashScreenPreview() {
    // Jangan lupa untuk menggunakan NavController dengan preview yang benar
    val navController = rememberNavController()
    SplashScreen(navController = navController)
}
