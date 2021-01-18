package com.example.movies.ui.movieslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.ui.adapter.MoviesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesListFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesListFragment()
    }
    private var adapter = MoviesAdapter()
    private var list: RecyclerView? = null
    private val viewModel: MoviesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_list_fragment, container, false)?.also {
            list = it?.findViewById<RecyclerView>(R.id.list)
            list?.layoutManager = LinearLayoutManager(this.activity)
adapter = MoviesAdapter()
            this.setupObservers()
        }
    }

    private fun setupObservers() {
        this.viewModel.moviesList.distinctUntilChanged().observe(viewLifecycleOwner, Observer {
            list?.adapter = adapter
            adapter.updateItems(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
       this.viewModel.loadMovies()
    }

}