package com.submission.dicoding.core.domain.repository

import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {

    fun getListGames(): Flow<Resource<List<Games>>>

    fun getDetailGames(id: Int): Flow<Resource<Games>>

    fun getDetailGamesFromSearch(id: Int): Flow<Resource<Games>>

    fun getFavoriteGames(): Flow<List<Games>>

    fun getSearchGames(name: String): Flow<Resource<List<Games>>>

    suspend fun setFavoriteGame(game: Games, state: Boolean)

    suspend fun insertGameFromSearch(game: Games, state: Boolean)

}