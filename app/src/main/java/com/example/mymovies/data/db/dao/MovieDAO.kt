package com.example.mymovies.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymovies.data.db.entity.MovieEntity

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTopRatedMovies(movies : List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE isActual = 0 ORDER BY average_rating DESC")
    suspend fun getTopRatedMovies () : List<MovieEntity>

    @Query("SELECT * FROM movie WHERE isActual = 1 ORDER BY title")
    suspend fun getActualMovies () : List<MovieEntity>

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun getMovieDetail(id : Int) : MovieEntity
}