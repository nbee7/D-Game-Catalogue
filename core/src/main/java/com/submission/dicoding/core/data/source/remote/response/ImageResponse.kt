package com.submission.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ImageResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("image")
    val image: String? = null
)