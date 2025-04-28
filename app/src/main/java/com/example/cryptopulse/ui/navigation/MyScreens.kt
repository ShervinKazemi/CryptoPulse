package com.example.cryptopulse.ui.navigation

sealed class MyScreens(route: String) {
    data object OnboardingScreen : MyScreens("onboardingScreen")
    data object HomeScreen : MyScreens("homeScreen")
    data object DetailScreen : MyScreens("detailScreen")
}