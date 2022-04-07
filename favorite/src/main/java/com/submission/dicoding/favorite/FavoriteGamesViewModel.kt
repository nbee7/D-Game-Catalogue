package com.submission.dicoding.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.dicoding.core.domain.usecase.GamesUseCase

class FavoriteGamesViewModel(gamesUseCase: GamesUseCase) : ViewModel() {

    val favoriteGames = gamesUseCase.getFavoriteGames().asLiveData()

}