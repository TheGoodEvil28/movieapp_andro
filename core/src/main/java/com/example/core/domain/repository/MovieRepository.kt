package com.example.core.domain.repository

import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(page: Int = 1): Flow<List<Movie>>
    fun getMovieDetail(id: Int): Flow<Movie?>
    fun getFavoriteMovies(): Flow<List<Movie>>
    suspend fun addFavorite(movie: Movie)
    suspend fun removeFavorite(movie: Movie)

    fun observeMovies(): Flow<List<Movie>>
    fun observeFavorites(): Flow<List<Movie>>
    fun observeMovieById(id: Int): Flow<Movie?>
    suspend fun setFavorite(id: Int, favorite: Boolean)
    suspend fun upsertMovie(movie: Movie)
}

