package com.example.mymovies.data.repository

import androidx.lifecycle.viewModelScope
import com.example.mymovies.data.db.MovieLocalDataSource
import com.example.mymovies.data.db.dao.MovieDAO
import com.example.mymovies.data.db.entity.MovieEntity
import com.example.mymovies.data.db.entity.toEntity
import com.example.mymovies.data.network.MovieRemote
import com.example.mymovies.data.network.MovieRemoteSource
import com.example.mymovies.data.network.MoviesApi
import com.example.mymovies.domain.model.Movie
import com.example.mymovies.util.MovieTypes
import kotlinx.coroutines.launch


/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MovieRepository{
        suspend fun getAllMyMoviesOfAGivenTypeFromApi(type : MovieTypes) : List<MovieEntity>{
                val moviesAPI : List<MovieRemote> = when(type){
                        MovieTypes.FAVOURITE -> MovieRemoteSource.Requests.GetPopularMovies().getMovies()
                        MovieTypes.ACTUAL -> MovieRemoteSource.Requests.GetActualMovies().getMovies()
                }
                return moviesAPI.toEntity(type)
        }





}
