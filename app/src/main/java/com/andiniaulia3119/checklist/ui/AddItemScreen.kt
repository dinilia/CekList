package com.andiniaulia3119.checklist.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.andiniaulia3119.checklist.data.Item
import com.andiniaulia3119.checklist.ui.theme.customFontFamily

@Composable
fun AddItemScreen(
    navController: NavController,
    itemViewModel: ItemViewModel,
    onBackToList: () -> Unit
) {
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Tambah Item Belanjaan",
            fontFamily = customFontFamily,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Nama Item",
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Thin
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = itemName,
                    onValueChange = { itemName = it },
                    placeholder = { Text("Masukkan nama item", fontFamily = customFontFamily,
                        fontWeight = FontWeight.Thin) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Kuantitas",
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Thin
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = itemQuantity,
                    onValueChange = { itemQuantity = it },
                    placeholder = { Text("Masukkan kuantitas", fontFamily = customFontFamily,
                        fontWeight = FontWeight.Thin) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            if (itemName.isBlank() || itemQuantity.isBlank()) {
                Toast.makeText(context, "Nama dan Kuantitas tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@Button
            }
            if (itemQuantity.toIntOrNull() == null) {
                Toast.makeText(context, "Kuantitas harus berupa angka", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val newItem = Item(name = itemName, quantity = itemQuantity)
            itemViewModel.addItem(newItem)
            onBackToList()
        }) {
            Text(
                "Tambah Item",
                fontFamily = customFontFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}






