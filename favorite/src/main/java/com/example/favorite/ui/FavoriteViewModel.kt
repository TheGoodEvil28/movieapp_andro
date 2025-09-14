package com.example.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repo: MovieRepository
) : ViewModel() {

    val favorites = repo.getFavoriteMovies().asLiveData()

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            if (movie.isFavorite) {
                repo.removeFavorite(movie)
            } else {
                repo.addFavorite(movie)
            }
        }
    }
}

