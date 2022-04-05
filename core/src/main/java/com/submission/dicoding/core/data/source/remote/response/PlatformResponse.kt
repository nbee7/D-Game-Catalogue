package com.submission.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PlatformResponse(

    @field:SerializedName("platform")
    val platform: PlatformItem
)

class PlatformItem(
    @SerializedName("slug")
    val slug: String
)

