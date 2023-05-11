package com.example.mymovies.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.data.network.MovieRemoteDataSource
import com.example.mymovies.data.repository.MovieRepository
import com.example.mymovies.domain.GetMovieDetailsUseCase
import com.example.mymovies.domain.GetMoviesUseCase

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MovieViewModelFactory(private val movieRepository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        MovieViewModel(
            GetMoviesUseCase(movieRepository),
            GetMovieDetailsUseCase(movieRepository)
        ) as T
}