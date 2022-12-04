package com.example.baseapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Movie")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var poster_path: String? = null,
    var overview: String? = null,
    var release_date: String? = null,
    var original_title: String? = null,
    var original_language: String? = null,
    var title: String? = null,
    var backdrop_path: String? = null,
    var popularity: Double,
    var vote_count: Int,
    var video: Boolean,
    var vote_average: Double

) : Serializable

//video
data class MovieVideo(
    var id: String,
    var key: String,
    var name: String,
    var site: String

) : Serializable

//genre
data class MovieGenre(
    var genresId: Int,
    var name: String?

) : Serializable
