package com.example.mymovies.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.data.db.entity.MovieEntity
import com.example.mymovies.data.repository.MovieRepository
import com.example.mymovies.domain.model.Movie
import com.example.mymovies.util.TypeOfMovie
import kotlinx.coroutines.launch

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTopMovies(){
        viewModelScope.launch {
            movieRepository.getAllTopRatedMoviesFromDatabase().forEach {
                println("MOVIE DB FAV:$it")
            }
        }
    }
    fun getActualMovies(){
        viewModelScope.launch {
            movieRepository.getAllActualMoviesFromDatabase().forEach {
                println("MOVIE DB RECENT: $it")
            }
        }
    }
    fun getApiActualMovies(){
        viewModelScope.launch {
            movieRepository.getAllMyMoviesOfAGivenTypeFromApi<Movie>(
                type = TypeOfMovie.ACTUAL
            ).forEach { println("API ACTUAL: $it") }
        }
    }
    fun getApiTopMovies(){
        viewModelScope.launch {
            movieRepository.getAllMyMoviesOfAGivenTypeFromApi<MovieEntity>(
                type = TypeOfMovie.FAVOURITE,
                ).forEach { println("API FAV: $it") }
            }

    }
}