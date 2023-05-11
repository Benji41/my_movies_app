package com.example.mymovies.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.databinding.ChildRecyclerviewMovieTypesBinding
import com.example.mymovies.domain.model.Movie

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/11/2023.
 */
class MovieCategoryAdapter(private val context: Context) : RecyclerView.Adapter<MovieCategoryAdapter.ViewHolder>() {

    private var movieList = emptyList<List<Movie>>()
    private val categories = listOf("Peliculas Favoritas","Peliculas en cartelera")


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):ViewHolder {
        val binding  = ChildRecyclerviewMovieTypesBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCategoryAdapter.ViewHolder, position: Int) {

            holder.binding.childRecyclerViewMovies.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            holder.binding.textViewCategoryMoviesTitle.text = categories[position]
            val moviesAdapter = MoviePosterAdapter(movieList[position])
            holder.binding.childRecyclerViewMovies.adapter = moviesAdapter


    }

    override fun getItemCount(): Int = movieList.size

    inner class ViewHolder(val binding: ChildRecyclerviewMovieTypesBinding) : RecyclerView.ViewHolder(binding.root)

    fun setData(movies : List<List<Movie>>){
        movieList = movies
        notifyDataSetChanged()
    }
}