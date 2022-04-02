package com.submission.dicoding.core.domain.usecase

import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.GameScreenshots
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gamesRepository: IGamesRepository) : GamesUseCase {
    override fun getListGames(): Flow<Resource<List<Games>>> = gamesRepository.getListGames()

    override fun getDetailGames(id: Int): Flow<Resource<Games>> = gamesRepository.getDetailGames(id)

    override fun getAllScreenshotByGame(id: Int): Flow<Resource<List<GameScreenshots>>> =
        gamesRepository.getAllScreenshotByGame(id)

}