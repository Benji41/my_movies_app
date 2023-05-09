package com.example.mymovies.data.repository

import com.example.mymovies.data.db.MovieLocalDataSource
import com.example.mymovies.data.db.entity.MovieEntity
import com.example.mymovies.data.db.entity.toEntity
import com.example.mymovies.data.network.MovieRemote
import com.example.mymovies.data.network.MovieRemoteSource
import com.example.mymovies.domain.model.Movie
import com.example.mymovies.domain.model.dbToDomain
import com.example.mymovies.domain.model.remoteToDomain
import com.example.mymovies.util.TypeOfMovie


/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MovieRepository{
        suspend inline fun <reified T> getAllMyMoviesOfAGivenTypeFromApi(type : TypeOfMovie) : List<T>{
                val moviesAPI : List<MovieRemote> = when(type){
                        TypeOfMovie.FAVOURITE -> MovieRemoteSource.Requests.GetPopularMovies().getMovies()
                        TypeOfMovie.ACTUAL -> MovieRemoteSource.Requests.GetActualMovies().getMovies()
                }
                return mapMovieList(moviesAPI,type)

        }
        @Suppress("UNCHECKED_CAST")
       inline fun <reified  T> mapMovieList( listOfMovies: List<MovieRemote>,type : TypeOfMovie) : List<T>{
               return (when(T::class){
                        Movie::class -> {listOfMovies.remoteToDomain()}
                        MovieEntity::class -> {listOfMovies.toEntity(type)}
                        else -> {}
                }) as List<T>

        }

        suspend fun getAllTopRatedMoviesFromDatabase() : List<Movie> =
                MovieLocalDataSource.getAllTopRatedMovies().dbToDomain()
        suspend fun getAllActualMoviesFromDatabase() : List<Movie> =
                MovieLocalDataSource.getAllActualMovies().dbToDomain()
        suspend fun insertAllMoviesToDatabase (movies: List<MovieEntity>){
                MovieLocalDataSource.insertAllMovies(movies)
        }

}


