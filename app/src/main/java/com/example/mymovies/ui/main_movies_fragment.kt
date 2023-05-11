package com.example.mymovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.mymovies.R
import com.example.mymovies.data.db.MovieLocalDataSource
import com.example.mymovies.databinding.FragmentMainMoviesFragmentBinding
import com.example.mymovies.ui.movies.viewmodel.MovieViewModel
import com.example.mymovies.ui.movies.viewmodel.MovieViewModelFactory
import com.example.mymovies.data.repository.MovieRepository
import com.example.mymovies.ui.adapters.MovieCategoryAdapter


class MainMovieFragment : Fragment(),MenuProvider {
    private var _binding: FragmentMainMoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private val movieCategoryAdapter: MovieCategoryAdapter  by lazy { MovieCategoryAdapter(requireContext()) }
    private val layoutManagerCategories: LayoutManager by lazy { LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false) }

    private val viewModel : MovieViewModel by viewModels {
        val repository = MovieRepository(MovieLocalDataSource())
        MovieViewModelFactory( repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMoviesFragmentBinding.inflate(inflater)
        setUpRV()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.allMovies.observe(viewLifecycleOwner){
            movieCategoryAdapter.setData(it)
        }

        requireActivity().addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    private fun setUpRV(){
        viewModel.onCreate()
        binding.parentRecyclerView.apply {
            adapter = movieCategoryAdapter
            layoutManager = layoutManagerCategories
        }
    }
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu,menu)
        val menuItem = menu.findItem(R.id.app_bar_search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Escriba un titulo"
        searchView.setOnQueryTextListener(
            object :  SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    TODO("Not yet implemented")
                }

            }
        )

    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        TODO("Not yet implemented")
    }


}