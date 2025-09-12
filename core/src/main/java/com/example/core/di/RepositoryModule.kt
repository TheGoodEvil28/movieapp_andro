package com.example.core.di

import com.example.core.data.repository.MovieRepositoryImpl
import com.example.core.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        repo: MovieRepositoryImpl
    ): MovieRepository
}

