package com.example.movieapp

import android.app.Application

import dagger.hilt.android.HiltAndroidApp
import net.sqlcipher.database.SQLiteDatabase

@HiltAndroidApp
class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SQLiteDatabase.loadLibs(this) // load SQLCipher sebelum database dipakai
    }
}
