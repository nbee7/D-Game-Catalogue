package com.submission.dicoding.core.domain.model

data class Games(
    val id: Int,
    val name: String,
    var image: String,
    val rating: Double,
    var description: String?,
    var released: String?,
    val genres: String,
    val platform: List<String>,
    val isFavorite: Boolean
)