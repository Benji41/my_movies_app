package com.example.mymovies.domain

import com.example.mymovies.data.repository.MovieRepository
import com.example.mymovies.domain.model.Movie

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/10/2023.
 */
class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) {
    suspend fun getMovieDetails( id : Int) : Movie = repository.getMovieDetails(id)

}