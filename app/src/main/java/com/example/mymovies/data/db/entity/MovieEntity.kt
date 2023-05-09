package com.example.mymovies.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mymovies.data.network.MovieRemote
import com.example.mymovies.data.network.ResponseMoviesAPI
import com.example.mymovies.util.MovieTypes
import org.jetbrains.annotations.NotNull

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/7/2023.
 */
@Entity(
    tableName = "movie"
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo( name = "detail_image")
    val backGround : String,
    @ColumnInfo( name = "poster_image")
    val poster  : String,
    @ColumnInfo( name = "title")
    val title : String,
    @ColumnInfo( name = "average_rating")
    val avg : Double,
    @ColumnInfo( name = "abstract")
    val overview : String,
    @NotNull
    @ColumnInfo( name = "isActual")
    val isActual : Boolean
)

fun List<MovieRemote>.toEntity(type : MovieTypes) : List<MovieEntity> {
    val mutableListOfMovieEntity : MutableList<MovieEntity> = mutableListOf()
    this.forEach {
        json ->
        val movieEntity = MovieEntity(
            id = json.id,
            backGround = json.imageBackGround,
            poster = json.imagePoster,
            title = json.title,
            avg = json.vote_average,
            overview = json.overview,
            isActual = type == MovieTypes.ACTUAL
        )
        mutableListOfMovieEntity+=movieEntity
    }
    return mutableListOfMovieEntity
}
