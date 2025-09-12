package com.example.core.domain.usecase

import com.example.core.domain.model.Movie
import com.example.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repo: MovieRepository) {
    operator fun invoke(page: Int = 1): Flow<List<Movie>> = repo.getPopularMovies(page)
}

