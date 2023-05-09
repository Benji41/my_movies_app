package com.example.mymovies.data.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/6/2023.
 */
data class MovieRemote(
    val id : Int,
    val title : String,
    val vote_average : Double,
    val overview : String,
    @SerializedName("backdrop_path")
    val imageBackGround : String,
    @SerializedName("poster_path")
    val imagePoster  : String,

)

data class ResponseMoviesAPI(
    val results : List<MovieRemote>
)