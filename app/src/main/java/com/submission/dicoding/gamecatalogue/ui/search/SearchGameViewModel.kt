package com.submission.dicoding.gamecatalogue.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.dicoding.core.domain.usecase.GamesUseCase

class SearchGameViewModel(private val gamesUseCase: GamesUseCase) : ViewModel() {

    fun getSearchGame(name: String) = gamesUseCase.getSearchGames(name).asLiveData()

}