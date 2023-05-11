package com.example.mymovies.util

import com.example.mymovies.domain.model.Movie

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/9/2023.
 */

suspend fun <T> List<T>.applyAndCheckNotEmpty(
    l : suspend List<T>.() -> Unit
){
    if (isNotEmpty()) l()
}

fun Movie.getCompleteImageURL(l : Movie.()->String) = "https://image.tmdb.org/t/p/w200/${l()}"