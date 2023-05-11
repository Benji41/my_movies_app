package com.example.mymovies.domain


import android.net.ConnectivityManager
import com.example.mymovies.data.db.entity.MovieEntity
import com.example.mymovies.data.repository.MovieRepository
import com.example.mymovies.domain.model.Movie
import com.example.mymovies.domain.model.movieListFromDBToDomain
import com.example.mymovies.util.TypeOfMovie
import com.example.mymovies.util.applyAndCheckNotEmpty
import java.nio.channels.NetworkChannel

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/8/2023.
 */
class GetMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke() :List<List<Movie>>{
        val actualMoviesToBeDisplayed = mutableListOf<Movie>()
        val favMoviesToBeDisplayed = mutableListOf<Movie>()

        //Movies to be stored in the database
        val playingNowMoviesAPI = repository.getAllMyMoviesOfAGivenTypeFromApi<MovieEntity>(TypeOfMovie.ACTUAL)
        val topFavMoviesAPI = repository.getAllMyMoviesOfAGivenTypeFromApi<MovieEntity>(TypeOfMovie.FAVOURITE)

        topFavMoviesAPI.applyAndCheckNotEmpty { repository.insertAllMoviesToDatabase(this) }
        playingNowMoviesAPI.applyAndCheckNotEmpty { repository.insertAllMoviesToDatabase(this) }

        actualMoviesToBeDisplayed.addAll(playingNowMoviesAPI.movieListFromDBToDomain().ifEmpty { repository.getAllActualMoviesFromDatabase() })
        favMoviesToBeDisplayed.addAll(topFavMoviesAPI.movieListFromDBToDomain().ifEmpty { repository.getAllTopRatedMoviesFromDatabase() })


        return listOf(favMoviesToBeDisplayed,actualMoviesToBeDisplayed)
    }

}