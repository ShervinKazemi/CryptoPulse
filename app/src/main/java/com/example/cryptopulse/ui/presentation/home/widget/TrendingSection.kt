package com.example.cryptopulse.ui.presentation.home.widget


import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.cryptopulse.model.data.TrendingData

@Composable
fun TrendingSection(
    title: String,
    navController: NavController,
    data: TrendingData,
) {

    HeaderSection(title)

}


