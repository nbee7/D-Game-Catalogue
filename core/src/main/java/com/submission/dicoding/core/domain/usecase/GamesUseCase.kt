package com.submission.dicoding.core.domain.usecase

import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {
    fun getAllGames(): Flow<Resource<List<Games>>>

    fun getResultSearchGames(query: String): Flow<Resource<List<Games>>>

    fun getDetailGames(id: Int): Flow<Resource<Games>>

    fun getFavoriteGames(): Flow<List<Games>>

    fun setFavoriteGames(games: Games, state: Boolean)
}