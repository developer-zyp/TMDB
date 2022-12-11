package com.example.tmdp.network

import com.example.tmdp.network.response.MovieDetailResponse
import com.example.tmdp.network.response.MovieResponse
import com.example.tmdp.network.response.MovieVideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(@Query("page") page: String): Response<MovieResponse>

    @GET("3/movie/now_playing")
    suspend fun getPlayingMovies(): Response<MovieResponse>

    @GET("3/movie/upcoming")
    suspend fun getUpComingMovies(): Response<MovieResponse>

    @GET("3/movie/{id}")
    suspend fun getMovieDetails(@Path("id") movieId: Int): Response<MovieDetailResponse>

    @GET("3/movie/{id}/videos")
    suspend fun getMovieVideos(@Path("id") movieId: Int): Response<MovieVideoResponse>

    @GET("3/search/movie")
    suspend fun getSearchMovies(@Query("query") search: String): Response<MovieResponse>

//    @POST("/api/sync")
//    fun setData(@Body data: ApiHelper) : Call<ResponseBody>
}