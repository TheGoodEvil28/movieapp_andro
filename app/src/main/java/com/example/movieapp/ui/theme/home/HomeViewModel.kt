package com.example.movieapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.MovieRepository
import com.example.core.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: MovieRepository,
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    val movies: StateFlow<List<Movie>> =
        repo.observeMovies()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        refreshMovies()
    }

    private fun refreshMovies() {
        viewModelScope.launch {
            getMoviesUseCase().collect { /* just trigger fetch */ }
        }
    }


    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            repo.setFavorite(movie.id, !movie.isFavorite)
        }
    }

}


