package com.submission.dicoding.core.data.source.remote

import android.util.Log
import com.submission.dicoding.core.data.source.remote.network.ApiResponse
import com.submission.dicoding.core.data.source.remote.network.ApiService
import com.submission.dicoding.core.data.source.remote.response.GameResponse
import com.submission.dicoding.core.data.source.remote.response.ImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getListGames(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.getListGames()
                val dataItem = response.games
                if (dataItem.isNotEmpty()) {
                    emit(ApiResponse.Success(response.games))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGame(id: Int): Flow<ApiResponse<GameResponse>> {
        return flow {
            try {
                val response = apiService.getDetailGames(id)
                if (response.name.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGamescreenshot(id: Int): Flow<ApiResponse<List<ImageResponse>>> {
        return flow {
            try {
                val response = apiService.getGameScreenshot(id)
                val data = response.results
                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.e("RemoteDataSource", e.message.toString())
            }
        }
    }
}