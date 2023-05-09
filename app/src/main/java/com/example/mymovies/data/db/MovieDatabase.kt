package com.example.mymovies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mymovies.data.db.dao.MovieDAO
import com.example.mymovies.data.db.entity.MovieEntity

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDAO: MovieDAO
}