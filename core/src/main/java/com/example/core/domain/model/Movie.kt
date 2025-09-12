package com.example.core.domain.model

data class Movie(
    val id: Int,
    val title: String?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val isFavorite: Boolean = false,
    val rating: Double? = null,               // optional, from API
    val genres: List<String> = emptyList()    // optional, from API
)// )
