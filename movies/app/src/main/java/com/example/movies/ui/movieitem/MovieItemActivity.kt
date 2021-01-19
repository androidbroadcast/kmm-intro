package com.example.movies.ui.movieitem

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import com.example.movies.data.MoviesItem
import com.example.movies.ui.base.NestedFragmentActivity
import com.example.movies.ui.util.ExtraKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieItemActivity : NestedFragmentActivity() {

    companion object {

        fun newIntent(context: Context, item: MoviesItem):Intent {
            val intent = Intent(context, MovieItemActivity ::class.java)
            intent.putExtra(ExtraKeys.MOVIE_ITEM, item)
            return intent
        }
    }

    override fun createFragment(): Fragment {
        val item = intent.getParcelableExtra<MoviesItem>(ExtraKeys.MOVIE_ITEM)
        return MovieItemFragment.newInstance(item)
    }
}