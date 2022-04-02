package com.submission.dicoding.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.submission.dicoding.core.data.source.local.entity.GameScreenshotEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameScreenshotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScreenshot(image: List<GameScreenshotEntity>)

    @Query("SELECT * FROM table_screeshot WHERE id = :id")
    fun getAllScreenshotByGame(id: Int): Flow<List<GameScreenshotEntity>>

}