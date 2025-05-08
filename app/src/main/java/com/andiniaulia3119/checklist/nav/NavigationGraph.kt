package com.andiniaulia3119.checklist.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andiniaulia3119.checklist.data.AppDatabase
import com.andiniaulia3119.checklist.data.ItemRepository
import com.andiniaulia3119.checklist.ui.AddItemScreen
import com.andiniaulia3119.checklist.ui.ItemListScreen
import com.andiniaulia3119.checklist.ui.ItemViewModel
import com.andiniaulia3119.checklist.ui.ItemViewModelFactory
import com.andiniaulia3119.checklist.ui.SplashScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") { SplashScreen(navController) }

        // Menambahkan ViewModel ke ItemListScreen
        composable("item_list_screen") {
            // Buat ItemViewModel dan pass ke composable
            val context = LocalContext.current
            val db = AppDatabase.getDatabase(context)
            val repository = ItemRepository(db.itemDao())
            val factory = ItemViewModelFactory(repository)
            val viewModel: ItemViewModel = viewModel(factory = factory)

            // Mempassing viewModel ke ItemListScreen
            ItemListScreen(navController = navController, viewModel = viewModel)
        }

        composable("addItem") {
            val context = LocalContext.current
            val db = AppDatabase.getDatabase(context)
            val repository = ItemRepository(db.itemDao())
            val factory = ItemViewModelFactory(repository)
            val viewModel: ItemViewModel = viewModel(factory = factory)

            // Passing viewModel ke AddItemScreen jika dibutuhkan
            AddItemScreen(navController = navController, viewModel = viewModel, onBackToList = {
                navController.navigate("item_list_screen") {
                    popUpTo("addItem") { inclusive = true }
                }
            })
        }
    }
}
