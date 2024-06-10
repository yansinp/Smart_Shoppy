package yansin.test.smartshoppy.core.navigation

sealed class NavigationScreen(val route:String){
    object Home : NavigationScreen("home")
    object Cart : NavigationScreen("cart")
    object Profile : NavigationScreen("profile")
}
