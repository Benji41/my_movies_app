package com.example.mymovies.data.repository

import com.example.mymovies.data.db.MovieLocalDataSource
import com.example.mymovies.data.db.entity.MovieEntity
import com.example.mymovies.data.db.entity.toEntity
import com.example.mymovies.data.network.MovieRemote
import com.example.mymovies.data.network.MovieRemoteDataSource
import com.example.mymovies.domain.model.Movie
import com.example.mymovies.domain.model.movieListFromDBToDomain
import com.example.mymovies.domain.model.movieDBToDomain
import com.example.mymovies.domain.model.remoteToDomain
import com.example.mymovies.util.TypeOfMovie


/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
class MovieRepository(
    private val movieLocalDataSource: MovieLocalDataSource,
){
        suspend inline fun <reified T> getAllMyMoviesOfAGivenTypeFromApi(type : TypeOfMovie) : List<T>{
            val movieRemoteSource = MovieRemoteDataSource()
                val moviesAPI : List<MovieRemote> = when(type){
                        TypeOfMovie.FAVOURITE -> movieRemoteSource.getPopularMovies.getMovies()
                        TypeOfMovie.ACTUAL -> movieRemoteSource.getActualMovies.getMovies()
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
            movieLocalDataSource.getAllTopRatedMovies().movieListFromDBToDomain()
        suspend fun getAllActualMoviesFromDatabase() : List<Movie> =
            movieLocalDataSource.getAllActualMovies().movieListFromDBToDomain()

        suspend fun insertAllMoviesToDatabase (movies: List<MovieEntity>){
            movieLocalDataSource.insertAllMovies(movies)
        }
        suspend fun getMovieDetails(id:Int) : Movie = movieLocalDataSource.getMovieDetail(id).movieDBToDomain()
}


