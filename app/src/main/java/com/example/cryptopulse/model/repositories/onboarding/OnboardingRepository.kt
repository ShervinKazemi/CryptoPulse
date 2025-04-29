package com.example.cryptopulse.model.repositories.onboarding

import com.example.cryptopulse.model.data.OnboardingData

interface OnboardingRepository {

    fun getOnboardingData() :List<OnboardingData>

}