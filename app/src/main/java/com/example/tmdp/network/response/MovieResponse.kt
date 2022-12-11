package com.example.tmdp.network.response

import com.example.tmdp.model.Movie
import com.example.tmdp.model.MovieGenre
import com.example.tmdp.model.MovieVideo

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

