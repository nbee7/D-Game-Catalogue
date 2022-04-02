package com.submission.dicoding.core.domain.repository

import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.GameScreenshots
import com.submission.dicoding.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface IGamesRepository {

    fun getListGames(): Flow<Resource<List<Games>>>

    fun getDetailGames(id: Int): Flow<Resource<Games>>

    fun getAllScreenshotByGame(id: Int): Flow<Resource<List<GameScreenshots>>>

}