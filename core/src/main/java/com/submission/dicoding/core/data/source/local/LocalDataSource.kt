package com.submission.dicoding.core.data.source.local

import com.submission.dicoding.core.data.source.local.entity.GameEntity
import com.submission.dicoding.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao) {

    suspend fun insertGame(games: List<GameEntity>) = gameDao.insertGame(games)

    suspend fun insertGameFromSearch(game: GameEntity, state: Boolean) {
        game.isFavorite = state
        gameDao.insertGameFromSearch(game)
    }

    suspend fun updateGame(game: GameEntity) = gameDao.updateGame(game)

    suspend fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateGame(game)
    }

    fun getListGames(): Flow<List<GameEntity>> = gameDao.getListGames()

    fun getDetailGame(id: Int): Flow<GameEntity> = gameDao.getDetailGame(id)

    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

}