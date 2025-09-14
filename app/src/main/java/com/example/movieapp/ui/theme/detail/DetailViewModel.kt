package com.example.movieapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.GetMovieDetailUseCase
import com.example.core.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val repo: MovieRepository
) : ViewModel() {

    private val _movieId = MutableStateFlow<Int?>(null)

    // Expose combined detail: network info + local favorite
    val detail: StateFlow<Movie?> = _movieId
        .filterNotNull()
        .flatMapLatest { id ->
            getMovieDetailUseCase(id).flatMapLatest { networkMovie ->
                repo.observeMovieById(id).map { localMovie ->
                    networkMovie?.let { nm ->
                        // Only copy if networkMovie is not null
                        nm.copy(isFavorite = localMovie?.isFavorite ?: nm.isFavorite)
                    }
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)
    fun load(id: Int) {
        _movieId.value = id
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            // Use current state for toggle
            repo.setFavorite(movie.id, !movie.isFavorite)
        }
    }
}
