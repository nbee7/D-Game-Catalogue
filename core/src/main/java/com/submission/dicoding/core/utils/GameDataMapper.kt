package com.submission.dicoding.core.utils

import com.submission.dicoding.core.data.source.local.entity.GameEntity
import com.submission.dicoding.core.data.source.remote.response.GameResponse
import com.submission.dicoding.core.domain.model.Games
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object GameDataMapper {

    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gamesList = ArrayList<GameEntity>()
        input.map { data ->
            var genre = ""
            for (i in data.genres.indices) {
                val itemName = data.genres[i].name
                genre = if (i == data.genres.lastIndex) {
                    "$genre $itemName"
                } else "$genre $itemName,"
            }
            val games = GameEntity(
                id = data.id,
                name = data.name,
                image = data.image,
                rating = data.rating,
                description = data.description,
                released = data.released,
                genre = genre,
                platform = data.platforms.map { it.platform.slug },
                isFavorite = false
            )
            gamesList.add(games)
        }
        return gamesList
    }

    fun mapResponsesToEntities(input: GameResponse): GameEntity {
        var genre = ""
        for (i in input.genres.indices) {
            val itemName = input.genres[i].name
            genre = if (i == input.genres.lastIndex) {
                "$genre $itemName"
            } else "$genre $itemName,"
        }
        return GameEntity(
            id = input.id,
            name = input.name,
            image = input.image,
            rating = input.rating,
            description = input.description,
            released = input.released,
            genre = genre,
            platform = input.platforms.map { it.platform.slug },
            isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Games> =
        input.map {
            Games(
                id = it.id,
                name = it.name,
                image = it.image,
                rating = it.rating,
                description = it.description,
                released = it.released,
                genres = it.genre,
                platform = it.platform,
                isFavorite = it.isFavorite
            )
        }

    fun mapEntitiesToDomain(input: GameEntity) =
        Games(
            id = input.id,
            name = input.name,
            image = input.image,
            rating = input.rating,
            description = input.description,
            released = input.released,
            genres = input.genre,
            platform = input.platform,
            isFavorite = input.isFavorite
        )

    fun mapDomainToEntity(input: Games) = GameEntity(
        id = input.id,
        name = input.name,
        image = input.image,
        rating = input.rating,
        description = input.description,
        released = input.released,
        genre = input.genres,
        platform = input.platform,
        isFavorite = input.isFavorite
    )

    fun mapResponsesToDomain(input: List<GameResponse>): Flow<List<Games>> {
        val gamesList = ArrayList<Games>()
        input.map { data ->
            var genre = ""
            for (i in data.genres.indices) {
                val itemName = data.genres[i].name
                genre = if (i == data.genres.lastIndex) {
                    "$genre $itemName"
                } else "$genre $itemName,"
            }
            val games = Games(
                id = data.id,
                name = data.name,
                image = data.image,
                rating = data.rating,
                description = data.description,
                released = data.released,
                genres = genre,
                platform = data.platforms.map { it.platform.slug },
                isFavorite = false
            )
            gamesList.add(games)
        }
        return flowOf(gamesList)
    }

    fun mapResponsesToDomain(input: GameResponse): Flow<Games> {
        var genre = ""
        for (i in input.genres.indices) {
            val itemName = input.genres[i].name
            genre = if (i == input.genres.lastIndex) {
                "$genre $itemName"
            } else "$genre $itemName,"
        }
        val games = Games(
            id = input.id,
            name = input.name,
            image = input.image,
            rating = input.rating,
            description = input.description,
            released = input.released,
            genres = genre,
            platform = input.platforms.map { it.platform.slug },
            isFavorite = false
        )
        return flowOf(games)
    }

}