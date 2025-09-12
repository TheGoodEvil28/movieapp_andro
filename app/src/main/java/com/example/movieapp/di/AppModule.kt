package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.core.data.repository.MovieRepositoryImpl
import com.example.core.data.source.local.MovieDao
import com.example.core.data.source.local.MovieDatabase
import com.example.core.data.source.remote.ApiService
import com.example.core.domain.repository.MovieRepository
import com.example.movieapp.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("tmdb_key")
    @Singleton
    fun provideTmdbKey(): String = BuildConfig.TMDB_API_KEY

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase =
        Room.databaseBuilder(context, MovieDatabase::class.java, "movie.db").build()

    @Provides
    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDao()
}

