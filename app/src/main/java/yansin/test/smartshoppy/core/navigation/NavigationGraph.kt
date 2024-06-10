package yansin.test.smartshoppy.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import yansin.test.smartshoppy.presentation.cart.CartScreen
import yansin.test.smartshoppy.presentation.home.HomeScreen
import yansin.test.smartshoppy.presentation.home.HomeViewModel
import yansin.test.smartshoppy.presentation.profile.ProfileScreen

@Composable
fun MainNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Home.route,
        modifier = Modifier
    ) {
        composable(NavigationScreen.Home.route) {
            val viewModel: HomeViewModel = hiltViewModel()
            val shopUiState = viewModel.shopState.value
            HomeScreen(navController, shopUiState)
        }
        composable(NavigationScreen.Cart.route) {
            CartScreen()
        }
        composable(NavigationScreen.Profile.route) {
            ProfileScreen()
        }
    }
}
