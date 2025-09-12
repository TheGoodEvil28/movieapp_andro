package com.example.core.data.repository

import com.example.core.data.source.local.MovieDao
import com.example.core.data.source.remote.ApiService
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.MovieRepository
import com.example.core.utils.MovieMapper.toDomain
import com.example.core.utils.MovieMapper.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val dao: MovieDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieRepository {

    override fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        val response = api.getPopularMovies(page)

        val movies = response.results.map { it.toDomain() } // langsung mapping ke domain

        // Cache ke DB tapi jangan reset isFavorite
        movies.forEach { movie ->
            val current = dao.observeById(movie.id).firstOrNull()
            val entity = movie.toEntity().copy(
                isFavorite = current?.isFavorite ?: false
            )
            dao.upsert(entity)
        }

        emitAll(
            dao.observeAll().map { entities ->
                entities.map { it.toDomain() }
            }
        )
    }.flowOn(dispatcher)

    override fun getMovieDetail(id: Int): Flow<Movie?> = flow {
        val response = api.getMovieDetail(id)
        emit(response.toDomain())
    }

    // ===== FAVORITE IMPLEMENTATION =====
    override fun getFavoriteMovies(): Flow<List<Movie>> =
        dao.getFavorites().map { entities ->
            entities.map { it.toDomain() }
        }

    override suspend fun addFavorite(movie: Movie) {
        dao.insertFavorite(movie.toEntity())
    }

    override suspend fun removeFavorite(movie: Movie) {
        dao.deleteFavorite(movie.toEntity())
    }

    override fun observeMovies(): Flow<List<Movie>> =
        dao.observeAll().map { entities ->
            entities.map { it.toDomain() }
        }.flowOn(dispatcher)

    override fun observeFavorites(): Flow<List<Movie>> =
        dao.observeFavorites().map { entities ->
            entities.map { it.toDomain() }
        }.flowOn(dispatcher)

    override fun observeMovieById(id: Int): Flow<Movie?> =
        dao.observeById(id).map { entity ->
            entity?.toDomain()
        }.flowOn(dispatcher)

    override suspend fun upsertMovie(movie: Movie) =
        withContext(dispatcher) { dao.upsert(movie.toEntity()) }

    override suspend fun setFavorite(id: Int, favorite: Boolean) =
        withContext(dispatcher) { dao.setFavorite(id, favorite) }
}
