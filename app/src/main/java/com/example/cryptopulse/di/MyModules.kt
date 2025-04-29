package com.example.cryptopulse.di

import com.example.cryptopulse.model.net.ApiService
import com.example.cryptopulse.model.net.createApiService
import com.example.cryptopulse.model.repositories.home.HomeRepository
import com.example.cryptopulse.model.repositories.home.HomeRepositoryImpl
import com.example.cryptopulse.model.repositories.onboarding.OnboardingRepository
import com.example.cryptopulse.model.repositories.onboarding.OnboardingRepositoryImpl
import com.example.cryptopulse.ui.presentation.home.HomeViewModel
import com.example.cryptopulse.ui.presentation.onboarding.OnboardingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single<ApiService> { createApiService() }

    single<OnboardingRepository> { OnboardingRepositoryImpl() }
    single<HomeRepository> { HomeRepositoryImpl(get()) }

    viewModel { OnboardingViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}