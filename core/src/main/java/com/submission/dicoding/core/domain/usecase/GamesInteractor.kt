package com.submission.dicoding.core.domain.usecase

import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.domain.repository.IGamesRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val gamesRepository: IGamesRepository) : GamesUseCase {
    override fun getAllGames(): Flow<Resource<List<Games>>> {
        TODO("Not yet implemented")
    }

    override fun getResultSearchGames(query: String): Flow<Resource<List<Games>>> {
        TODO("Not yet implemented")
    }

    override fun getDetailGames(id: Int): Flow<Resource<Games>> {
        TODO("Not yet implemented")
    }

    override fun getFavoriteGames(): Flow<List<Games>> {
        TODO("Not yet implemented")
    }

    override fun setFavoriteGames(games: Games, state: Boolean) {
        TODO("Not yet implemented")
    }
}