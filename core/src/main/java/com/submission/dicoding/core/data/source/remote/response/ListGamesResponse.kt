package com.submission.dicoding.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGamesResponse(
	@field:SerializedName("results")
	val games: List<GameResponse>
)