package id.barakkastudio.nebenginaja.ui.screen.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import id.barakkastudio.nebenginaja.ui.navigation.model.BottomBarScreen
import id.barakkastudio.nebenginaja.ui.navigation.model.GeneralScreen
import id.barakkastudio.nebenginaja.ui.screen.cart.CartScreen
import id.barakkastudio.nebenginaja.ui.screen.detail.DetailScreen
import id.barakkastudio.nebenginaja.ui.screen.home.HomeScreen
import id.barakkastudio.nebenginaja.ui.screen.profile.ProfileScreen
import id.barakkastudio.nebenginaja.ui.screen.search.SearchScreen

/** Created by github.com/im-o on 5/13/2023. */

@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                },
                navigateToSearch = {
                    navController.navigate(GeneralScreen.SearchProduct.route)
                }
            )
        }
        composable(BottomBarScreen.Cart.route) {
            CartScreen(
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                }
            )
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(
            route = GeneralScreen.DetailProduct.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType }),
        ) {
            val id = it.arguments?.getInt("productId") ?: -1
            DetailScreen(
                productId = id,
                navigateBack = {
                    navController.navigateUp()
                },
            )
        }
        composable(
            route = GeneralScreen.SearchProduct.route,
        ) {
            SearchScreen(
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}