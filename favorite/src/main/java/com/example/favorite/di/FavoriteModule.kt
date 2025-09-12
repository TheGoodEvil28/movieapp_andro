//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.FragmentComponent
//
//@Module
//@InstallIn(FragmentComponent::class)
//object FavoriteModule {
//
//    @Provides
//    fun provideDummy(): String = "dummy"
//}

package com.example.favorite.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FavoriteModule {

    @Provides
    fun provideDummy(): String = "dummy"
}
