package com.example.mymovies.ui.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.domain.GetMovieDetailsUseCase
import com.example.mymovies.domain.GetMoviesUseCase
import com.example.mymovies.domain.model.Movie
import kotlinx.coroutines.launch

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MovieViewModel(private val getMoviesUseCase: GetMoviesUseCase, private val getMovieDetailsUseCase: GetMovieDetailsUseCase) : ViewModel() {
    private val _topRatedMovies  = MutableLiveData<List<Movie>>()
    val topRatedMovies : LiveData<List<Movie>> get() = _topRatedMovies

    private val _actualMovies  = MutableLiveData<List<Movie>>()
    val playingNowMovies : LiveData<List<Movie>> get() = _actualMovies

    private val _singleMovie : MutableLiveData<Movie?> = MutableLiveData<Movie?>()
    val singleMovie : LiveData<Movie?>  get() = _singleMovie

    val allMovies = MutableLiveData<List<List<Movie>>>()


    fun onCreate() {
        viewModelScope.launch {
            val movies = getMoviesUseCase()
            allMovies.value = movies
            movies.forEachIndexed { i, m ->
                when(i){
                    0 -> _topRatedMovies.value = m.ifEmpty { emptyList() }
                    else -> _actualMovies.value = m.ifEmpty { emptyList() }
                }
            }
        }

    }
    fun getMovieDetails(id : Int){
        _singleMovie.postValue(null)
        viewModelScope.launch {
            _singleMovie.postValue(getMovieDetailsUseCase.getMovieDetails(id))
        }

    }




}