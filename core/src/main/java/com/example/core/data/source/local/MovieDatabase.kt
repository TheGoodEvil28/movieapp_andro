package com.example.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.entity.MovieEntity

//import com.example.core.data.source.local.Mocv
@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDatabase? = null

    }
}
