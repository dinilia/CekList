package com.andiniaulia3119.checklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andiniaulia3119.checklist.data.AppDatabase
import com.andiniaulia3119.checklist.data.ItemRepository
import com.andiniaulia3119.checklist.ui.AddItemScreen
import com.andiniaulia3119.checklist.ui.EditItemScreen
import com.andiniaulia3119.checklist.ui.ItemListScreen
import com.andiniaulia3119.checklist.ui.ItemViewModel
import com.andiniaulia3119.checklist.ui.ItemViewModelFactory
import com.andiniaulia3119.checklist.ui.SplashScreen
import com.andiniaulia3119.checklist.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                CekListApp()
            }
        }
    }
}

@Composable
fun CekListApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val database = AppDatabase.getDatabase(context)
    val repository = ItemRepository(database.itemDao())
    val itemViewModel: ItemViewModel = viewModel(
        factory = ItemViewModelFactory(repository)
    )

    NavHost(
        navController = navController,
        startDestination = "splash"  // Ganti startDestination menjadi "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("itemList") {
            ItemListScreen(navController, itemViewModel)
        }
        composable("addItem") {
            AddItemScreen(navController, itemViewModel, onBackToList = {
                navController.navigate("itemList")
            })
        }
        composable("editItem/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull()
            val items by itemViewModel.items.collectAsState()
            val item = items.find { it.id.toInt() == itemId }

            if (item != null) {
                EditItemScreen(
                    navController = navController,
                    itemViewModel = itemViewModel,
                    item = item,
                    onBackToList = { navController.navigate("itemList") }
                )
            }
        }
    }
}
