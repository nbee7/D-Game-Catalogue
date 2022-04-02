package com.submission.dicoding.core.domain.usecase

import com.submission.dicoding.core.data.Resource
import com.submission.dicoding.core.domain.model.GameScreenshots
import com.submission.dicoding.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {
    fun getListGames(): Flow<Resource<List<Games>>>

    fun getDetailGames(id: Int): Flow<Resource<Games>>

    fun getAllScreenshotByGame(id: Int): Flow<Resource<List<GameScreenshots>>>

}