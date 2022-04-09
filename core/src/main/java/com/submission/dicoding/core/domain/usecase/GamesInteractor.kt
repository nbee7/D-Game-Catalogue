package com.submission.dicoding.core.domain.usecase

import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gamesRepository: IGamesRepository) : GamesUseCase {
    override fun getListGames(): Flow<Resource<List<Games>>> =
        gamesRepository.getListGames()

    override fun getDetailGames(id: Int): Flow<Resource<Games>> =
        gamesRepository.getDetailGames(id)

    override fun getDetailGamesFromSearch(id: Int): Flow<Resource<Games>> =
        gamesRepository.getDetailGamesFromSearch(id)

    override fun getFavoriteGames(): Flow<List<Games>> =
        gamesRepository.getFavoriteGames()

    override fun getSearchGames(name: String): Flow<Resource<List<Games>>> =
        gamesRepository.getSearchGames(name)

    override suspend fun setFavoriteGame(game: Games, state: Boolean) =
        gamesRepository.setFavoriteGame(game, state)

    override suspend fun insertGameFromSearch(game: Games, state: Boolean) {
        gamesRepository.insertGameFromSearch(game, state)
    }

}