package com.submission.dicoding.core.data.source.local.room

import androidx.room.*
import com.submission.dicoding.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(games: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameFromSearch(games: GameEntity)

    @Update
    suspend fun updateGame(game: GameEntity)

    @Query("SELECT * FROM table_game")
    fun getListGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM table_game WHERE id = :id")
    fun getDetailGame(id: Int): Flow<GameEntity>

    @Query("SELECT * FROM table_game WHERE isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>
}