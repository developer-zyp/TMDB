package com.example.baseapp.network.response

import com.example.baseapp.model.Movie
import com.example.baseapp.model.MovieGenre
import com.example.baseapp.model.MovieVideo

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

data class MovieDetailResponse(
    var id: Int,
    var backdrop_path: String? = null,
    var overview: String? = null,
    var poster_path: String? = null,
    var release_date: String? = null,
    var title: String? = null,
    var genres: List<MovieGenre>? = null,
    var video: Boolean,
    var vote_average: Double,
    var vote_count: Int
)


data class MovieVideoResponse(
    var id: Int,
    var results: List<MovieVideo>
)

