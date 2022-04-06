package com.submission.dicoding.core.data

import com.submission.dicoding.core.data.source.local.LocalDataSource
import com.submission.dicoding.core.data.source.remote.RemoteDataSource
import com.submission.dicoding.core.data.source.remote.network.ApiResponse
import com.submission.dicoding.core.data.source.remote.response.GameResponse
import com.submission.dicoding.core.domain.model.Games
import com.submission.dicoding.core.domain.repository.IGamesRepository
import com.submission.dicoding.core.utils.GameDataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GamesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGamesRepository {

    override fun getListGames(): Flow<Resource<List<Games>>> =
        object : NetworkBoundResource<List<Games>, List<GameResponse>>() {

            override fun loadFromDB(): Flow<List<Games>> {
                return localDataSource.getListGames().map {
                    GameDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Games>?): Boolean =
                data.isNullOrEmpty() || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getListGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gamesList = GameDataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gamesList)
            }

        }.asFlow()

    override fun getDetailGames(id: Int): Flow<Resource<Games>> =
        object : NetworkBoundResource<Games, GameResponse>() {
            override fun loadFromDB(): Flow<Games> {
                return localDataSource.getDetailGame(id).map {
                    GameDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: Games?): Boolean {
                return data?.description == null
            }

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> =
                remoteDataSource.getDetailGame(id)

            override suspend fun saveCallResult(data: GameResponse) {
                val games = GameDataMapper.mapResponsesToEntities(data)
                localDataSource.updateGame(games)
            }

        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Games>> {
        return localDataSource.getFavoriteGames().map {
            GameDataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchGames(name: String): Flow<Resource<List<Games>>> =
        object : NetworkBoundResource<List<Games>, List<GameResponse>>() {

            override fun loadFromDB(): Flow<List<Games>> {
                return localDataSource.getSearchGame(name).map {
                    GameDataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Games>?): Boolean =
                data.isNullOrEmpty() || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.searchGames(name)

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gamesList = GameDataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gamesList)
            }

        }.asFlow()

    override suspend fun setFavoriteGame(game: Games, state: Boolean) {
        val gameEntity = GameDataMapper.mapDomainToEntity(game)
        localDataSource.setFavoriteGame(gameEntity, state)
    }
}