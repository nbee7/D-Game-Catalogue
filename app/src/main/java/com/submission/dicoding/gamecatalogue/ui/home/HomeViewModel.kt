package com.submission.dicoding.gamecatalogue.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.dicoding.core.domain.usecase.GamesUseCase

class HomeViewModel(gamesUseCase: GamesUseCase) : ViewModel() {

    val listGames = gamesUseCase.getListGames().asLiveData()

}