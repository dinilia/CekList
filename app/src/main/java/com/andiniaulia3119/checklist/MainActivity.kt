package com.andiniaulia3119.checklist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.andiniaulia3119.checklist.ui.AddItemScreen
import com.andiniaulia3119.checklist.ui.ItemListScreen
import com.andiniaulia3119.checklist.ui.theme.CheckListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CheckListTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "itemList") {
                    composable("itemList") {
                        ItemListScreen(
                            onNavigateToAddItem = {
                                navController.navigate("addItem")
                            }
                        )
                    }
                    composable("addItem") {
                        AddItemScreen(
                            onBackToList = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
