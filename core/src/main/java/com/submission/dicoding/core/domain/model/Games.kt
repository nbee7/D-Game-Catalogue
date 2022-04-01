package com.submission.dicoding.core.domain.model

class Games(
    val id: Int,
    val name: String,
    var image: String,
    val rating: Double,
    var description: String,
    val genres: String,
    val isFavorite: Boolean
)