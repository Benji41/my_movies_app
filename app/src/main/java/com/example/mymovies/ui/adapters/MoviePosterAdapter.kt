package com.example.mymovies.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.databinding.RecyclerviewMovieElementBinding
import com.example.mymovies.domain.model.Movie
import com.example.mymovies.util.getCompleteImageURL
import com.squareup.picasso.Picasso

/**
 * Created by Noé Benjamín Reynoso Aguirre on 5/11/2023.
 */
class MoviePosterAdapter(val movies : List<Movie>) : RecyclerView.Adapter<MoviePosterAdapter.ViewHolder>() {
    lateinit var context: Context
    inner class ViewHolder(val binding: RecyclerviewMovieElementBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewMovieElementBinding.inflate(LayoutInflater.from(parent.context))
        context = parent.context
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int  = movies.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        Picasso.get().load(movie.getCompleteImageURL { imagePoster }).into(holder.binding.cardViewMoviePoster)
        holder.binding.cardViewMovieTitle.text = movie.title
        holder.binding.cardViewMovieRating.text = movie.avgVote.toString()
        holder.binding.cardViewMoviePoster.setOnClickListener{
            Toast.makeText(context, movie.title,Toast.LENGTH_LONG).show()
        }
    }


}