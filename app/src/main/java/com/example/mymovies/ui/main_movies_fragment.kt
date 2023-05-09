package com.example.mymovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.mymovies.databinding.FragmentMainMoviesFragmentBinding
import com.example.mymovies.ui.movies.viewmodel.MovieViewModel
import com.example.mymovies.ui.movies.viewmodel.MovieViewModelFactory
import com.example.mymovies.data.db.MovieLocalDataSource
import com.example.mymovies.data.network.MovieRemoteSource
import com.example.mymovies.data.repository.MovieRepository


class MainMovieFragment : Fragment() {
    private var _binding: FragmentMainMoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MovieViewModel by viewModels {
        val repository = MovieRepository()
        MovieViewModelFactory( repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMoviesFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       
    }

}