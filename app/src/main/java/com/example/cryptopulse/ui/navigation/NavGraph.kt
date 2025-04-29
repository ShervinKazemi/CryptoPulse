package com.example.cryptopulse.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptopulse.ui.presentation.home.HomeScreen
import com.example.cryptopulse.ui.presentation.onboarding.OnboardingScreen
import dev.burnoo.cokoin.navigation.KoinNavHost

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    KoinNavHost(navController = navController, startDestination = MyScreens.OnboardingScreen.route) {
        composable(route = MyScreens.OnboardingScreen.route) {
            OnboardingScreen(navController)
        }
        composable(route = MyScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
    }
}