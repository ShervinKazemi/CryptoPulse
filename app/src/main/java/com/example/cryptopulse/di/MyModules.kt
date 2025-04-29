package com.example.cryptopulse.di

import com.example.cryptopulse.model.repositories.onboarding.OnboardingRepository
import com.example.cryptopulse.model.repositories.onboarding.OnboardingRepositoryImpl
import com.example.cryptopulse.ui.presentation.onboarding.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {
    single<OnboardingRepository> { OnboardingRepositoryImpl() }

    viewModel { OnboardingViewModel(get()) }
}