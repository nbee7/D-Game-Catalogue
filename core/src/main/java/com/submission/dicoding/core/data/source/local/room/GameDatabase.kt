package com.submission.dicoding.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.submission.dicoding.core.data.source.local.entity.GameEntity
import com.submission.dicoding.core.data.source.local.entity.GameScreenshotEntity

@Database(
    entities = [GameEntity::class, GameScreenshotEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(PlatformConverter::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

    abstract fun gameScreenshotDao(): GameScreenshotDao
}