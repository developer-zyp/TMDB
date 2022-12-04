package com.example.baseapp.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp.common.Utils
import com.example.baseapp.database.DBHelper
import com.example.baseapp.model.Movie
import com.example.baseapp.model.MovieVideo
import com.example.baseapp.network.ApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {

    private val dbHelper = DBHelper()
    private val apiRepository = ApiRepository()

    val movieTrailers = MutableLiveData<List<MovieVideo>>()

    fun getMovieTrailers(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val value = apiRepository.getMovieTrailers(movieId)
                movieTrailers.postValue(value)
            } catch (ex: Exception) {
                movieTrailers.postValue(emptyList())
                Utils.showToast(ex.message.toString())
            }

        }
    }

    fun getSingleMovie(movieId: Int): LiveData<Movie> =
        dbHelper.getSingleMovie(movieId)


    fun insertMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            dbHelper.insertMovie(movie)
        }

    }

    fun deleteMovie(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            dbHelper.deleteMovie(movie)
        }
    }

    fun getAllMovies(): LiveData<List<Movie>> =
        dbHelper.getAllMovies
}