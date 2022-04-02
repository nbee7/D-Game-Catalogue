package com.submission.dicoding.core.utils

import com.submission.dicoding.core.data.source.local.entity.GameScreenshotEntity
import com.submission.dicoding.core.data.source.remote.response.ImageResponse
import com.submission.dicoding.core.domain.model.GameScreenshots

object GameScreenshotDataMapper {

    fun mapResponsesToEntities(input: List<ImageResponse>): List<GameScreenshotEntity> {
        val imageList = ArrayList<GameScreenshotEntity>()
        input.map {
            val image = GameScreenshotEntity(
                id = it.id,
                image = it.image
            )
            imageList.add(image)
        }
        return imageList
    }

    fun mapEntitiesToDomain(input: List<GameScreenshotEntity>): List<GameScreenshots> =
        input.map {
            GameScreenshots(
                id = it.id,
                image = it.image
            )
        }
}