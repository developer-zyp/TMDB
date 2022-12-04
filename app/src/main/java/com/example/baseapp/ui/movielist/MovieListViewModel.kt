package com.example.baseapp.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseapp.model.Movie
import com.example.baseapp.network.ApiRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val apiRepository = ApiRepository()

    val popularMovie = MutableLiveData<List<Movie>>()
    val playingMovie = MutableLiveData<List<Movie>>()
    val upcomingMovie = MutableLiveData<List<Movie>>()
    val searchMovies = MutableLiveData<List<Movie>>()
    var page: Int = 1

    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.value = "Exception : ${throwable.localizedMessage}"
        loading.value = false
    }

    fun getPopularMovies() {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            val value = apiRepository.getPopularMovies((page).toString())
            popularMovie.postValue(value)
            loading.value = false
        }
    }

    fun getPlayingMovies() {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            val value = apiRepository.getPlayingMovies()
            playingMovie.postValue(value)
            loading.value = false
        }
    }

    fun getUpcomingMovies() {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            val value = apiRepository.getUpcomingMovies()
            upcomingMovie.postValue(value)
            loading.value = false
        }
    }

    fun getSearchMovies(query: String) {
        viewModelScope.launch(exceptionHandler) {
            val value = apiRepository.getSearchMovies(query)
            searchMovies.postValue(value)
        }
    }


}