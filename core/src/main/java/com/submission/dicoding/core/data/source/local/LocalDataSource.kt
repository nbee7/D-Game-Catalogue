package com.submission.dicoding.core.data.source.local

import com.submission.dicoding.core.data.source.local.entity.GameEntity
import com.submission.dicoding.core.data.source.local.entity.GameScreenshotEntity
import com.submission.dicoding.core.data.source.local.room.GameDao
import com.submission.dicoding.core.data.source.local.room.GameScreenshotDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val gameDao: GameDao,
    private val gameScreenshotDao: GameScreenshotDao
) {

    suspend fun insertGame(games: List<GameEntity>) = gameDao.insertGame(games)

    suspend fun insertScreenshot(image: List<GameScreenshotEntity>) =
        gameScreenshotDao.insertScreenshot(image)

    suspend fun updateGame(game: GameEntity) = gameDao.updateGame(game)

    fun getListGames(): Flow<List<GameEntity>> = gameDao.getListGames()

    fun getDetailGame(id: Int): Flow<GameEntity> = gameDao.getDetailGame(id)

    fun getGameScreenshot(id: Int): Flow<List<GameScreenshotEntity>> =
        gameScreenshotDao.getAllScreenshotByGame(id)

}