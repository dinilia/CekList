package com.andiniaulia3119.checklist.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andiniaulia3119.checklist.ui.ItemListScreen
import com.andiniaulia3119.checklist.ui.SplashScreen


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") { SplashScreen(navController) }
        composable("item_list_screen") { ItemListScreen(navController) }
    }
}