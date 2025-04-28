package com.example.cryptopulse.ui.navigation

sealed class MyScreens(val route: String) {
    data object OnboardingScreen : MyScreens("onboardingScreen")
    data object HomeScreen : MyScreens("homeScreen")
    data object DetailScreen : MyScreens("detailScreen")
}