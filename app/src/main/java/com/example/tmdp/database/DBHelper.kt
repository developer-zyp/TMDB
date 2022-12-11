package com.example.tmdp.database

import androidx.lifecycle.LiveData
import com.example.tmdp.AppInstance
import com.example.tmdp.model.Movie

class DBHelper {

    private val db: AppDatabase = AppDatabase.getInstance(AppInstance.getInstance())

    val getAllMovies: LiveData<List<Movie>> = db.movieDao().getAll()

    fun getSingleMovie(movieId: Int): LiveData<Movie> {
        return db.movieDao().getDataById(movieId)
    }

    suspend fun insertMovie(movie: Movie) {
        db.movieDao().insertData(movie)
    }

    suspend fun deleteMovie(movie: Movie) {
        db.movieDao().deleteData(movie)
    }

//    fun getUsers(): MutableList<Movie>?{
//        val movies: MutableList<Movie>? = mutableListOf<Movie>()
//        GlobalScope.async {
//            db?.movieDao?.getAll()?.forEach {
//                movies?.add(it)
//            }
//        }
//
//        return movies
//    }


}