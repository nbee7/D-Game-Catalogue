package com.submission.dicoding.gamecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.domain.usecase.GamesUseCase
import kotlinx.coroutines.launch

class DetailGameViewModel(private val gamesUseCase: GamesUseCase) : ViewModel() {

    fun getDetailGame(id: Int) = gamesUseCase.getDetailGames(id).asLiveData()

    fun getDetailGameFromSearch(id: Int) = gamesUseCase.getDetailGamesFromSearch(id).asLiveData()

    fun insertGameFromSearch(game: Games, state: Boolean) {
        viewModelScope.launch {
            gamesUseCase.insertGameFromSearch(game, state)
        }
    }

    fun setFavoriteGame(game: Games, state: Boolean) {
        viewModelScope.launch {
            gamesUseCase.setFavoriteGame(game, state)
        }
    }

}