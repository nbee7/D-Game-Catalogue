package com.submission.dicoding.gamecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.submission.dicoding.core.domain.usecase.GamesUseCase

class DetailGameViewModel(private val gamesUseCase: GamesUseCase) : ViewModel() {

    fun getDetailGame(id: Int) = gamesUseCase.getDetailGames(id).asLiveData()

    fun getImageGame(id: Int) = gamesUseCase.getAllScreenshotByGame(id).asLiveData()

}