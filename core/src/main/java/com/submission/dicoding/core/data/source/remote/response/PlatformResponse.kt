package com.submission.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlatformResponse(

    @field:SerializedName("slug")
    val name: String? = null
)
