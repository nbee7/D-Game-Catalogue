package com.submission.dicoding.core.data.source.remote.network

import com.submission.dicoding.core.data.source.remote.response.GameResponse
import com.submission.dicoding.core.data.source.remote.response.ListGamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getListGames(
        @Query("page_size") size: Int,
        @Query("key") key: String
    ): ListGamesResponse

    @GET("games")
    suspend fun searchGames(
        @Query("search") query: String,
        @Query("page_size") size: Int,
        @Query("key") key: String
    ): ListGamesResponse

    @GET("games/{id}")
    suspend fun getDetailGames(
        @Path("id") id: Int,
        @Query("key") key: String
    ): GameResponse
}