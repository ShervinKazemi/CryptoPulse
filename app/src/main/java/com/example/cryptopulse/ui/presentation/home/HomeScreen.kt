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
import com.example.cryptopulse.ui.navigation.MyScreens
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

    when {
        trendingData is UiState.Loading -> {
            LoadingScreen()
        }
        trendingData is UiState.Success && coinsData is UiState.Success -> {
            val trendingCoins = (trendingData as UiState.Success<TrendingData>).data.coins.take(10)
            val coins = (coinsData as UiState.Success<CoinsData>).data

            Scaffold(
                topBar = { HomeAppBar() },
                containerColor = MaterialTheme.colorScheme.background
            ) { innerPadding ->
                HomeContent(
                    paddingValues = innerPadding,
                    trendingCoins = trendingCoins,
                    coins = coins,
                    navController = navController
                )
            }
        }
        else -> {
            ErrorScreen(navController)
        }
    }
}

@Composable
private fun HomeContent(
    paddingValues: PaddingValues,
    trendingCoins: List<TrendingData.Coin>,
    coins: CoinsData,
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
                coins = trendingCoins,
                navController = navController
            )
        }

        item(key = "top_coins_header") {
            HeaderSection(
                title = "Top Coins 🚀",
                navController = navController,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        items(
            items = coins,
            key = { it.id }
        ) { coin ->
            CoinCard(
                coin = coin,
                onClick = {
//                    navController.navigate(MyScreens.DetailScreen.route +"/${coin.id}")
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}