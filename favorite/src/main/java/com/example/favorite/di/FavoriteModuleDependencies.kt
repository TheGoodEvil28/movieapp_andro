package com.example.favorite.di

import com.example.core.domain.repository.MovieRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun movieRepository(): MovieRepository
}
