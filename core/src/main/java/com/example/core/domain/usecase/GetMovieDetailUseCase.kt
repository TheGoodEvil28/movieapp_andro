package com.example.core.domain.usecase

import com.example.core.domain.model.Movie
import com.example.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repo: MovieRepository) {
    operator fun invoke(id: Int): Flow<Movie?> = repo.getMovieDetail(id)
}

