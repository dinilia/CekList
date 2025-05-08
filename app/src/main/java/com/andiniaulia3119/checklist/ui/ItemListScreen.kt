package com.andiniaulia3119.checklist.ui

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andiniaulia3119.checklist.ui.theme.CheckListTheme

@Composable
fun ItemListScreen(navController: NavController) {
    val items = listOf<String>() // Nanti diganti jadi data dari database
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (items.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center // ini bikin konten ada di tengah layar secara vertikal
            ) {
                Text(
                    text = "Belum ada list belanjaan nih :( \nSilakan tambahkan list Anda!",
                    style = MaterialTheme.typography.headlineSmall.copy( // lebih besar dan tebal
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center // teks rata tengah
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        Toast.makeText(context, "Tombol Tambah Item", Toast.LENGTH_SHORT).show()
                        navController.navigate("addItem")
                    },
                    modifier = Modifier.fillMaxWidth(0.4f)
                ) {
                    Text("Tambah Item")
                }
            }
        } else {
            // Jika ada item, tampilkan dalam daftar
            LazyColumn {
                items(items.size) { index ->
                    Text(
                        text = items[index],
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewItemListScreen() {
    CheckListTheme {
        ItemListScreen(navController = NavController(context = LocalContext.current))
    }
}