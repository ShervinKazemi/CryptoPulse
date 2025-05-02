package com.example.cryptopulse.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptopulse.model.data.CoinsData
import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.model.repositories.home.HomeRepository
import com.example.cryptopulse.util.UiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {

    val trendingData: StateFlow<UiState<TrendingData>> = flow {
        emitAll(homeRepository.getTrendingCoins())
    }
        .map<TrendingData, UiState<TrendingData>> { UiState.Success(it) }
        .catch { emit(UiState.Error(it.message ?: "Failed to load trending coins")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )

    val coinsData: StateFlow<UiState<CoinsData>> = flow {
        emitAll(homeRepository.getCoinsList())
    }
        .map<CoinsData, UiState<CoinsData>> { UiState.Success(it) }
        .catch { emit(UiState.Error(it.message ?: "Failed to load coins list")) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Loading
        )
}