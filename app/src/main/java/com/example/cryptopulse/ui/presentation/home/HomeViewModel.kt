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
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) :ViewModel() {
    private val _trendingData = MutableStateFlow<UiState<TrendingData>>(UiState.Loading)
    val trendingData: StateFlow<UiState<TrendingData>> = _trendingData.asStateFlow()


    fun getTrendingCoins() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _trendingData.value = UiState.Loading
            try {
                val response = homeRepository.getTrendingCoins()
                _trendingData.value = UiState.Success(data = response)
            } catch (e: Exception) {
                _trendingData.value = UiState.Error(e.message.toString())
            }
        }
    }

}