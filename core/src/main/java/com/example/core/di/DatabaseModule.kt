//package com.example.core.di
//
//import android.content.Context
//import androidx.room.Room
//import com.example.core.data.source.local.MovieDao
//import com.example.core.data.source.local.MovieDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//    @Provides
//    @Singleton
//    fun provideDb(@ApplicationContext context: Context): MovieDatabase =
//        Room.databaseBuilder(
//            context,
//            MovieDatabase::class.java,
//            "movie.db"
//        )
//            .fallbackToDestructiveMigration() // 🟢 development mode (hapus DB lama kalau schema berubah)
//            .build()
//
//    @Provides
//    fun provideMovieDao(db: MovieDatabase): MovieDao = db.movieDao()
//}
