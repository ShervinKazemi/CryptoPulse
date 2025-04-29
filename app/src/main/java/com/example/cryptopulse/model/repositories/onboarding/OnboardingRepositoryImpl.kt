package com.example.cryptopulse.model.repositories.onboarding

import com.example.cryptopulse.R
import com.example.cryptopulse.model.data.OnboardingData

class OnboardingRepositoryImpl :OnboardingRepository {
    override fun getOnboardingData(): List<OnboardingData> {
        val onboardingData: List<OnboardingData> = listOf(
            OnboardingData(
                image = R.drawable.onboarding_one,
                title = "Welcome to the Crypto World",
                description = "Track live prices and market changes - fast, clean, and always " +
                        "with you"
            ),
            OnboardingData(
                image = R.drawable.onboarding_two,
                title = "Live Prices, No Noise.",
                description = "Stay updated with real-time data. Just what you need - no fluff."
            ),
            OnboardingData(
                image = R.drawable.onboarding_three,
                title = "You're All Set",
                description = "Jump into the market and feel the pulse of crypto.\nLet's go."
            )
        )

        return onboardingData
    }
}