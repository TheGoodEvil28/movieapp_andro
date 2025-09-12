package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.domain.model.Movie

object MovieMapper {

    // Network → Domain
    fun MovieResponse.Movie.toDomain(): Movie = Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath?.let { Constants.IMAGE_BASE + it },
        releaseDate = releaseDate,
        isFavorite = false,                  // from network, always false
        rating = voteAverage,
        genres = genres?.map { it.name } ?: emptyList()
    )

    // Entity → Domain (local DB)
    fun MovieEntity.toDomain(): Movie = Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        isFavorite = isFavorite,
        rating = null,                        // DB may not store rating
        genres = emptyList()                  // DB may not store genres
    )

    // Domain → Entity (for saving to DB)
    fun Movie.toEntity(): MovieEntity = MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        isFavorite = isFavorite
    )
}
