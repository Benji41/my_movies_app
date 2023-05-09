package com.example.mymovies.ui.movies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.data.db.entity.MovieEntity
import com.example.mymovies.data.db.MovieLocalDataSource
import com.example.mymovies.data.db.entity.toEntity
import com.example.mymovies.data.network.MovieRemoteSource
import com.example.mymovies.data.repository.MovieRepository
import kotlinx.coroutines.launch

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {





}