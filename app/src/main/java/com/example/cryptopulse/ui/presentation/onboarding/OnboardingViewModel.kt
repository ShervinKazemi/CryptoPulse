package com.example.cryptopulse.ui.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptopulse.model.data.OnboardingData
import com.example.cryptopulse.model.repositories.onboarding.OnboardingRepository
import com.example.cryptopulse.util.coroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel(private val onboardingRepository: OnboardingRepository) : ViewModel() {

    private val _onboardingData = MutableStateFlow<List<OnboardingData>>(emptyList())
    val onboardingData: StateFlow<List<OnboardingData>> = _onboardingData

    init {
        fetchOnboardingData()
    }

    private fun fetchOnboardingData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val data = onboardingRepository.getOnboardingData()
            _onboardingData.value = data
        }
    }
}