package com.jsn.quickgrab.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jsn.quickgrab.screens.check_out.CheckOutScreen
import com.jsn.quickgrab.screens.home.HomeScreen
import com.jsn.quickgrab.screens.product_details.ProductDetailsScreen

@Composable
fun QuickGrabNavigation(modifier: Modifier = Modifier) {

    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Home) {
        composable(Home) {
            HomeScreen(navHostController)
        }

        composable(ProductDetails) {
            ProductDetailsScreen(navHostController)
        }

        composable(Checkout) {
            CheckOutScreen(navHostController)
        }
    }

}