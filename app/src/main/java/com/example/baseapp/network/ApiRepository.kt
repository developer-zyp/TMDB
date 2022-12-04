package com.example.baseapp.network

import com.example.baseapp.model.Movie
import com.example.baseapp.model.MovieVideo

class ApiRepository {

    private val api = ApiClient.apiService

    suspend fun getPopularMovies(page: String): List<Movie> {
        return api.getPopularMovies(page).body()?.results ?: emptyList()
    }

    suspend fun getPlayingMovies(): List<Movie> {
        return api.getPlayingMovies().body()?.results ?: emptyList()
    }

    suspend fun getUpcomingMovies(): List<Movie> {
        return api.getUpComingMovies().body()?.results ?: emptyList()
    }

    suspend fun getSearchMovies(query: String): List<Movie> {
        return api.getSearchMovies(query).body()?.results ?: emptyList()
    }

    suspend fun getMovieTrailers(movieId: Int): List<MovieVideo> {
        return api.getMovieVideos(movieId).body()?.results ?: emptyList()
    }

//    region comment
//    companion object {
//        var movieData = MutableLiveData<Movie>()
//        fun getMovieApiCall(callback: (List<Movie>?) -> Unit) {
//            val call = apiServiceInterface.getMovie("1")
//            call.enqueue(object : Callback<List<Movie>> {
//                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
//                    callback(response.body())
//                }
//
//                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                    callback(null)
//                }
//            })
//        }
//    }
//    endregion

}