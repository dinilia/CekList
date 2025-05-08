package com.andiniaulia3119.checklist.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andiniaulia3119.checklist.data.Item
import com.andiniaulia3119.checklist.ui.theme.CheckListTheme

@Composable
fun ItemListScreen(onNavigateToAddItem: () -> Unit) {
    val items = emptyList<Item>() // Gantilah dengan data dari ViewModel

    // Cek jika daftar item kosong
    if (items.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Belum ada list belanjaan. Silahkan tambahkan list Anda.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onNavigateToAddItem) {
                Text("Tambah List Belanjaan")
            }
        }
    } else {
        // Tampilkan daftar item jika ada
        LazyColumn {
            items(items) { item ->
                Text(text = item.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewItemListScreen() {
    CheckListTheme {
        ItemListScreen(onNavigateToAddItem = {})
    }
}