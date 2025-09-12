package com.example.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.source.local.MovieDao
import com.example.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

