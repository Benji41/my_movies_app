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
    }
}
