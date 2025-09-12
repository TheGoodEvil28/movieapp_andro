// D:\MovieApp\core\src\main\java\com\example\core\data\source\remote\response\MovieResponse.kt
package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results")
    val results: List<Movie>
) {
    data class Movie(
        @SerializedName("id")
        val id: Int,

        @SerializedName("title")
        val title: String?,

        @SerializedName("overview")
        val overview: String?,

        @SerializedName("poster_path")
        val posterPath: String?,

        @SerializedName("release_date")
        val releaseDate: String?,

        @SerializedName("vote_average")
        val voteAverage: Double?,   // Add this if API returns it

        @SerializedName("genres")
        val genres: List<Genre>?    // Add this if API returns a list of genre objects
    )

    data class Genre(
        @SerializedName("id")
        val id: Int,

        @SerializedName("name")
        val name: String
    )
}

