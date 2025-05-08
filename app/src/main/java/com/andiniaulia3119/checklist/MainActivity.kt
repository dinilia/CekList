package com.andiniaulia3119.checklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andiniaulia3119.checklist.data.AppDatabase
import com.andiniaulia3119.checklist.data.ItemRepository
import com.andiniaulia3119.checklist.ui.AddItemScreen
import com.andiniaulia3119.checklist.ui.ItemListScreen
import com.andiniaulia3119.checklist.ui.ItemViewModel
import com.andiniaulia3119.checklist.ui.ItemViewModelFactory
import com.andiniaulia3119.checklist.ui.SplashScreen

// MainActivity.kt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CekListApp()
        }
    }
}

@Composable
fun CekListApp() {
    val navController = rememberNavController()

    // Inisialisasi ViewModel
    val itemViewModel: ItemViewModel = viewModel(
        factory = ItemViewModelFactory(ItemRepository(AppDatabase.getDatabase(LocalContext.current).itemDao()))
    )

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("itemList") {
            ItemListScreen(navController = navController, viewModel = itemViewModel)
        }
        composable("addItem") {
            AddItemScreen(
                navController = navController,
                viewModel = itemViewModel,
                onBackToList = {
                    navController.navigate("itemList") {
                        popUpTo("addItem") { inclusive = true }
                    }
                }
            )
        }
    }
}

