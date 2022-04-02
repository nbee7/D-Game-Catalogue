package com.submission.dicoding.core.utils

import com.submission.dicoding.core.data.source.local.entity.GameEntity
import com.submission.dicoding.core.data.source.remote.response.GameResponse
import com.submission.dicoding.core.domain.model.Games

object GameDataMapper {

    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity> {
        val gamesList = ArrayList<GameEntity>()
        input.map {
            var genre = ""
            for (i in it.genres.indices) {
                val itemName = it.genres[i].name
                genre = if (i == it.genres.lastIndex) {
                    "$genre $itemName"
                } else "$genre $itemName,"
            }
            val games = GameEntity(
                id = it.id,
                name = it.name,
                image = it.image,
                rating = it.rating,
                description = it.description,
                released = it.released,
                genre = genre,
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
        isFavorite = input.isFavorite
    )

}