package com.submission.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("background_image")
    val image: String? = null,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("description_raw")
    val description: String? = null,

    @field:SerializedName("released")
    val released: String? = null,

    @field:SerializedName("genres")
    val genres: List<GenreResponse>,

    @field:SerializedName("parent_platforms")
    val platforms: List<PlatformResponse>
    )