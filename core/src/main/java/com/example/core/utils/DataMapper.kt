package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity // ✅ ini yang bener
import com.example.core.data.source.remote.response.MovieResponse // pastikan sesuai
import com.example.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToDomain(input: List<MovieResponse.Movie>): List<Movie> =
        input.map { mapResponseToDomain(it) }

    fun mapResponseToDomain(it: MovieResponse.Movie): Movie =
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            posterPath = it.posterPath?.let { path -> Constants.IMAGE_BASE + path },
            releaseDate = it.releaseDate,
            isFavorite = false // from network, always false by default
        )

    fun mapEntitiesToDomain(entities: List<MovieEntity>): List<Movie> =
        entities.map { mapEntityToDomain(it) }

    fun mapDomainToEntity(domain: Movie): MovieEntity =
        MovieEntity(
            id = domain.id,
            title = domain.title,
            overview = domain.overview,
            posterPath = domain.posterPath,
            releaseDate = domain.releaseDate,
            isFavorite = domain.isFavorite // ✅ keep favorite flag
        )

    fun mapEntityToDomain(entity: MovieEntity): Movie =
        Movie(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            isFavorite = entity.isFavorite // ✅ keep favorite flag
        )



    // Extensions (optional, biar enak dipanggil langsung)
    fun MovieEntity.toDomain(): Movie = mapEntityToDomain(this)
    fun Movie.toEntity(): MovieEntity = mapDomainToEntity(this)
}
