package com.example.core.di

import com.example.core.data.source.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideApiKey(@Named("tmdb_key") apiKey: String) = apiKey

    @Provides
    fun provideApiInterceptor(@Named("tmdb_key") apiKey: String): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val newUrl = original.url.newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()
        val req = original.newBuilder().url(newUrl).build()
        chain.proceed(req)
    }

    @Provides
    @Singleton
    fun provideOkHttp(clientInterceptor: Interceptor): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val certificatePinner = CertificatePinner.Builder()
            // ini domain utama TMDB API
            .add("api.themoviedb.org",
                "sha256/f78NVAesYtdZ9OGSbK7VtGQkSIVykh3DnduuLIJHMu4=",
                "sha256/G9LNNAql897egYsabashkzUCTEJkWBzgoEtk8X/678c=",
                "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=") // ganti fingerprint
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(clientInterceptor)
            .addInterceptor(logger)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/") // check this
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)


}
