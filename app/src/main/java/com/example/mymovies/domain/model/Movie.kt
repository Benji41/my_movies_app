package com.example.mymovies.domain.model

import com.example.mymovies.data.db.entity.MovieEntity


/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
data class Movie(
    val id : Int,
    val imageBackGround : String,
    val imagePoster  : String,
    val title : String,
    val avgVote : Double,
    val overview : String
)

fun List<MovieEntity>.toDomain() : List<Movie>{
    val mutableListOfMovies : MutableList<Movie> = mutableListOf()

    this.forEach {
        movieEntity ->

        val movie = Movie(
            id = movieEntity.id,
            imageBackGround = movieEntity.backGround,
            imagePoster = movieEntity.poster,
            title = movieEntity.title,
            avgVote = movieEntity.avg,
            overview = movieEntity.overview
        )
        mutableListOfMovies +=movie
    }
    return mutableListOfMovies

}