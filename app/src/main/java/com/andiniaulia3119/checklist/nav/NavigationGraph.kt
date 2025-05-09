package com.andiniaulia3119.checklist.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
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

@Composable
fun NavigationGraph(navController: NavHostController) {
    val navController = rememberNavController()
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val repository = remember { ItemRepository(db.itemDao()) }
    val itemViewModel: ItemViewModel = viewModel(
        factory = ItemViewModelFactory(repository)
    )

    NavHost(navController = navController, startDestination = "itemList") {
        composable("itemList") {
            ItemListScreen(navController, itemViewModel)
        }

        composable("addItem") {
            AddItemScreen(
                navController = navController,
                itemViewModel = itemViewModel,
                onBackToList = { navController.navigate("itemList") }
            )
        }

        composable("editItem/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toLongOrNull()
            val items by itemViewModel.items.collectAsState()

            val item = items.find { it.id == itemId }
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

