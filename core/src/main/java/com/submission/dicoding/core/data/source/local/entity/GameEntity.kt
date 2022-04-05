package com.submission.dicoding.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_game")
data class GameEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "slug")
    val name: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "released")
    var released: String? = null,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "platforms")
    var platform: List<String>,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean
)