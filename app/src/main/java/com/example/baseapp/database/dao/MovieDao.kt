package com.example.baseapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.baseapp.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getAll(): LiveData<List<Movie>>

    @Query("SELECT * FROM Movie WHERE id = :Id")
    fun getDataById(Id: Int): LiveData<Movie>

    @Insert
    suspend fun insertData(movie: Movie)

    @Insert
    suspend fun insertList(movies: List<Movie>)

    @Update
    suspend fun updateData(movie: Movie)

    @Delete
    suspend fun deleteData(movie: Movie)

    @Query("DELETE FROM Movie")
    suspend fun deleteAll()
}