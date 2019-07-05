package id.aasumitro.made.ui.main.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.R
import id.aasumitro.made.base.BaseFragment
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.ui.detail.DetailActivity
import id.aasumitro.made.ui.detail.DetailActivity.Companion.DATA_ENTITY
import id.aasumitro.made.ui.detail.DetailActivity.Companion.EXTRA_DATA
import id.aasumitro.made.ui.detail.DetailActivity.Companion.MOVIE
import id.aasumitro.made.ui.rv.adapter.MovieAdapter
import id.aasumitro.made.ui.rv.listener.MovieListener
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MovieFragment : BaseFragment(R.layout.fragment_movie), MovieListener, MovieNavigator {

    private val mViewModel: MovieViewModel by lazy {
        ViewModelProviders
            .of(activity as FragmentActivity)
            .get(MovieViewModel::class.java)
    }

    override fun initView() {
        mViewModel.initVM(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
    }

    private fun initList() {
        movie_recycler_view.setHasFixedSize(true)
        val mLayoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(activity, 2)
        movie_recycler_view.layoutManager = mLayoutManager
        movie_recycler_view.itemAnimator = DefaultItemAnimator()
        mViewModel.movies().observe(this, Observer {
            movie_recycler_view.adapter = MovieAdapter(it, this)
        })
    }

    override fun onMovieSelected(movie: Movie?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DATA_ENTITY, MOVIE)
        intent.putExtra(EXTRA_DATA, movie)
        startActivity(intent)
    }

    override fun onLoadData(status: Boolean) {
        onProgress(status)
    }

    override fun onErrorCallback(message: String) {
        onError(message)
    }

}
