package com.submission.dicoding.gamecatalogue.di

import com.submission.dicoding.core.domain.usecase.GamesInteractor
import com.submission.dicoding.core.domain.usecase.GamesUseCase
import com.submission.dicoding.gamecatalogue.ui.detail.DetailGameViewModel
import com.submission.dicoding.gamecatalogue.ui.home.HomeViewModel
import com.submission.dicoding.gamecatalogue.ui.search.SearchGameViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GamesUseCase> { GamesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailGameViewModel(get()) }
    viewModel { SearchGameViewModel(get()) }
}