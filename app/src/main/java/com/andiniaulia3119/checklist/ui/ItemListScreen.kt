package com.andiniaulia3119.checklist.ui

import android.content.res.Configuration
import android.widget.Toast
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andiniaulia3119.checklist.ui.theme.CheckListTheme

@Composable
fun ItemListScreen(navController: NavController) {
    // Contoh data item kosong, nanti bisa diganti dengan data dinamis
    val items = listOf<String>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tombol untuk menambah item
        Button(
            onClick = {
                // Logika untuk menambah item
                Toast.makeText(navController.context, "Tombol Tambah Item", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menampilkan daftar item (kosong untuk saat ini)
        LazyColumn {
            items(items.size) { index ->
                Text(text = items[index], style = MaterialTheme.typography.bodyLarge)
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