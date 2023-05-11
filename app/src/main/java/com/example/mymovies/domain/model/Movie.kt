package com.example.mymovies.domain.model

import com.example.mymovies.data.db.entity.MovieEntity
import com.example.mymovies.data.network.MovieRemote


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

fun List<MovieEntity>.movieListFromDBToDomain() : List<Movie>{
    return this.map{
        Movie(
            it.id,
            it.backGround,
            it.poster,
            it.title,
            it.avg,
            it.overview
        )
    }
}

fun List<MovieRemote>.remoteToDomain() : List<Movie>{
    return this.map{
        Movie(
            it.id,
            it.imageBackGround,
            it.imagePoster,
            it.title,
            it.vote_average,
            it.overview
        )
    }
}

fun MovieEntity.movieDBToDomain() : Movie{
    return Movie(
        this.id,this.backGround,this.poster,this.title,this.avg,this.overview
    )
}

