package com.example.mymovies.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.data.repository.MovieRepository

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MovieViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MovieViewModel(movieRepository) as T
}
class MovieDetailViewModelFactory(private val id : Int,private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MovieDetailViewModelFactory(id,movieRepository) as T
}