package com.example.movies.ui.movieslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.MovieItemModel
import com.example.movies.R
import com.example.movies.shared.viewmodel.MoviesListViewModel
import com.example.movies.ui.adapter.MoviesAdapter
import com.example.movies.ui.movieitem.MovieItemActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesListFragment()
    }

    private var adapter :MoviesAdapter? = null
    private var list: RecyclerView? = null
    private val viewModel = MoviesListViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)?.also {
            list = it?.findViewById<RecyclerView>(R.id.list)
            list?.layoutManager = LinearLayoutManager(this.activity)
            adapter = MoviesAdapter(this::onMovieClick)
        }
    }

    private fun setupObservers() {
        this.viewModel.moviesList.bind {
            list?.adapter = adapter
            adapter?.updateItems(it?.results ?: arrayListOf())
            adapter?.notifyDataSetChanged()
        }
    }

    fun onMovieClick(index: Int) {
        this.viewModel.getMovie(index)?.let {
            activity?.startActivity(MovieItemActivity.newIntent(requireActivity(),
                MovieItemModel(it)))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.setupObservers()
    }

    override fun onResume() {
        super.onResume()
        this.viewModel.loadMovies()
    }

    override fun onPause() {
        super.onPause()
    }
}