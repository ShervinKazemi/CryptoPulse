package com.example.cryptopulse.ui.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cryptopulse.model.data.CoinsData
import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.ui.presentation.home.widget.CoinCard
import com.example.cryptopulse.ui.presentation.home.widget.ErrorScreen
import com.example.cryptopulse.ui.presentation.home.widget.HeaderSection
import com.example.cryptopulse.ui.presentation.home.widget.HomeAppBar
import com.example.cryptopulse.ui.presentation.home.widget.LoadingScreen
import com.example.cryptopulse.ui.presentation.home.widget.MarketOverviewSection
import com.example.cryptopulse.ui.presentation.home.widget.TrendingSection
import com.example.cryptopulse.util.UiState
import dev.burnoo.cokoin.viewmodel.getViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = getViewModel()
) {
    val trendingData by viewModel.trendingData.collectAsState()
    val coinsData by viewModel.coinsData.collectAsState()

    when (val trendingState = trendingData) {
        is UiState.Loading -> LoadingScreen()
        is UiState.Success -> {
            val coinsDataState = coinsData as? UiState.Success
            if (coinsDataState != null) {
                val trendingCoins = remember(trendingState.data) {
                    trendingState.data.coins.take(10)
                }
                Scaffold(
                    topBar = { HomeAppBar() },
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    MainContent(
                        innerPadding,
                        trendingCoins,
                        coinsDataState.data,
                        navController
                    )
                }
            } else {
                ErrorScreen(navController)
            }
        }
        is UiState.Error -> ErrorScreen(navController)
    }
}

@Composable
private fun MainContent(
    paddingValues: PaddingValues,
    coins: List<TrendingData.Coin>,
    data: CoinsData,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item(key = "market_overview") {
            MarketOverviewSection()
        }
        item(key = "trending_section") {
            TrendingSection(
                title = "Trending 🔥",
                coins = coins,
                navController = navController
            )
        }
        // Moved TopCoins content into MainContent
        item(key = "top_coins_header") {
            HeaderSection(
                title = "Top Coins 🚀",
                navController = navController,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        items(data, key = { it.id }) { coin ->
            CoinCard(
                coin = coin,
                onClick = { /* Navigate to coin details */ },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}