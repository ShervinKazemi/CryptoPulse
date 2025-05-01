package com.example.cryptopulse.ui.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.ui.presentation.home.widget.ErrorScreen
import com.example.cryptopulse.ui.presentation.home.widget.HomeAppBar
import com.example.cryptopulse.ui.presentation.home.widget.LoadingScreen
import com.example.cryptopulse.ui.presentation.home.widget.MarketOverviewSection
import com.example.cryptopulse.ui.presentation.home.widget.TrendingSection
import com.example.cryptopulse.util.UiState
import dev.burnoo.cokoin.viewmodel.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = getViewModel<HomeViewModel>()
) {
    val trendingData by viewModel.trendingData.collectAsState()

    when (trendingData) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> {
            val data = (trendingData as UiState.Success).data
            val coins = data.coins.take(10)

            Scaffold(
                topBar = { HomeAppBar() },
                containerColor = MaterialTheme.colorScheme.background
            ) { innerPadding ->
                MainContent(innerPadding, coins, navController)
            }
        }
        is UiState.Error -> ErrorScreen(navController)
    }
}

@Composable
private fun MainContent(paddingValues: PaddingValues, coins: List<TrendingData.Coin>, navController:
NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            MarketOverviewSection()
        }
        item {
            TrendingSection(title = "Trending 🔥" , coins = coins , navController = navController)
        }
    }
}