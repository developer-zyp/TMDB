package com.example.baseapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.baseapp.model.Movie
import com.example.baseapp.network.ApiRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val apiRepository = ApiRepository()
    val myMovies: MutableLiveData<List<Movie>> = MutableLiveData()
    val context = application
    var page: Int = 1

    val errorMessage = MutableLiveData<String>()

    private var movieLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val movieResult: MutableLiveData<List<Movie>>
        get() {
            return movieLiveData
        }

//    init {
//        getPopularMovies()
//    }

//    private fun getMovies() {
//        viewModelScope.launch(Dispatchers.IO) {
//            ApiRepository.getMovieApiCall {
//                movieLiveData.postValue(it)
//            }
//        }
//    }

    fun getPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch(exceptionHandler) {
            val response = apiRepository.getPopularMovies((page).toString())
//            if (response.isSuccessful) {
//                movieLiveData.postValue(response.body()?.results ?: listOf())
//            } else {
//                errorMessage.value = response.message()
//            }
            myMovies.value = response
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.value = "Exception handled: ${throwable.localizedMessage}"
    }

//    fun initDatabase() {
//        val daoMovie = MoviesRoomDatabase.getInstance(context).getMovieDao()
//        REALIZATION = MoviesRepositoryRealization(daoMovie)
//
//    }


}