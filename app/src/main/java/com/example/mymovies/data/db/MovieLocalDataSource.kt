package com.example.mymovies.data.db

import androidx.room.Room
import com.example.mymovies.data.db.entity.MovieEntity
import com.example.mymovies.util.MyApplication

class MovieLocalDataSource {
    companion object{
        private val room : MovieDatabase by lazy {
            Room.databaseBuilder(MyApplication.appContext, MovieDatabase::class.java,"Movies Database")
                .build()
        }
        suspend fun insertAllMovies(movies : List<MovieEntity>){
           room.movieDAO.insertAllTopRatedMovies(movies)
        }
        suspend fun getAllTopRatedMovies() : List<MovieEntity> = room.movieDAO.getTopRatedMovies()
        suspend fun getAllActualMovies() : List<MovieEntity> = room.movieDAO.getActualMovies()
        suspend fun getMovieDetail(id : Int) : MovieEntity= room.movieDAO.getMovieDetail(id)



    }
}
