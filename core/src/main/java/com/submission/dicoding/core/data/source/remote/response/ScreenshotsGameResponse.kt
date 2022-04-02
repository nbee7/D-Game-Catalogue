package com.submission.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ScreenshotsGameResponse(

    @field:SerializedName("results")
    val results: List<ImageResponse>
)

