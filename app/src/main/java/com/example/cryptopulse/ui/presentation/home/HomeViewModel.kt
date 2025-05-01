package com.example.cryptopulse.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptopulse.model.data.TrendingData
import com.example.cryptopulse.model.repositories.home.HomeRepository
import com.example.cryptopulse.util.UiState
import com.example.cryptopulse.util.coroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(private val homeRepository: HomeRepository) :ViewModel() {
    private val _trendingData = MutableStateFlow<UiState<TrendingData>>(UiState.Loading)
    val trendingData: StateFlow<UiState<TrendingData>> = _trendingData.asStateFlow()

    init {
        getTrendingCoins()
    }
    private fun getTrendingCoins() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _trendingData.update { UiState.Loading }
            try {
                val response = homeRepository.getTrendingCoins()
                _trendingData.update { UiState.Success(data = response) }
            } catch (e: IOException) {
                _trendingData.update { UiState.Error(e.message.toString()) }
            }
        }
    }

}