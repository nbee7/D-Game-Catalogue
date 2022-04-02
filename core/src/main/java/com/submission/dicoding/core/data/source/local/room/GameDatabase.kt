package com.submission.dicoding.core.data.source.local.room

import androidx.room.Database
import com.submission.dicoding.core.data.source.local.entity.GameEntity

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class GameDatabase {

    abstract fun gameDao(): GameDao

    abstract fun gameScreenshotDao(): GameScreenshotDao
}