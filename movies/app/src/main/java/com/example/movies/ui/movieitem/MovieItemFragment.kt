package com.example.movies.ui.movieitem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.distinctUntilChanged
import com.example.movies.MovieItemModel
import com.example.movies.R
import com.example.movies.shared.data.MoviesItem
import com.example.movies.ui.util.ExtraKeys
import com.example.movies.ui.util.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieItemFragment : Fragment() {

    companion object {
        fun newInstance(item: MovieItemModel?): MovieItemFragment {
            val f = MovieItemFragment()
            var bundle = Bundle()
            bundle.putParcelable(ExtraKeys.MOVIE_ITEM,item)
            f.arguments = bundle
            return f
        }
    }
    private  var image: ImageView? = null
    private var titleText: TextView? = null
    private  var overviewText: TextView? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false).also {
            image = it.findViewById<ImageView>(R.id.movie_image)
            titleText = it.findViewById<TextView>(R.id.movie_title)
            overviewText = it.findViewById<TextView>(R.id.movie_text)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupObservers()

        arguments?.getParcelable<MovieItemModel>(ExtraKeys.MOVIE_ITEM)?.let {
            viewModel.setupItem(it)
        }
    }

    private fun setupObservers() {
        this.viewModel.movieItem.distinctUntilChanged().observe(viewLifecycleOwner, Observer {
            titleText?.text = it.title
            overviewText?.text = it.overview
            image?.loadImage(it.imagePath())
        })
    }

}