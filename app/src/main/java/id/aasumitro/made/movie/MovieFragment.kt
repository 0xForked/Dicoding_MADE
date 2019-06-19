package id.aasumitro.made.movie

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.DetailActivity
import id.aasumitro.made.DetailActivity.Companion.DATA_ENTITY
import id.aasumitro.made.DetailActivity.Companion.EXTRA_DATA
import id.aasumitro.made.DetailActivity.Companion.MOVIE
import id.aasumitro.made.R
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MovieFragment : Fragment(), MovieListener {
    private var mPoster: TypedArray? = null
    private var mTitle: Array<String>? = null
    private var mOverview: Array<String>? = null
    private var mRating: Array<String>? = null
    private var mAdapter: MovieAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        addMovie()
        initList()
    }

    private fun initData() {
        mPoster = resources.obtainTypedArray(R.array.data_movie_poster)
        mTitle = resources.getStringArray(R.array.data_movie_title)
        mOverview = resources.getStringArray(R.array.data_movie_overview)
        mRating = resources.getStringArray(R.array.data_movie_rating)
    }

    private fun addMovie() {
        val mMovies = ArrayList<Movie>()
        for (i in 0 until mTitle?.size as Int) {
            val mMovie = Movie()
            mMovie.poster = (mPoster?.getResourceId(i, -1) as Int)
            mMovie.title = mTitle?.get(i)
            mMovie.overview = mOverview?.get(i)
            mMovie.rating = mRating?.get(i)
            mMovies.add(mMovie)
        }
        mAdapter = MovieAdapter(mMovies, this)
    }

    private fun initList() {
        movie_recycler_view.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager =
            LinearLayoutManager(activity)
        movie_recycler_view.layoutManager = layoutManager
        movie_recycler_view.itemAnimator = DefaultItemAnimator()
        movie_recycler_view.adapter = mAdapter
    }

    override fun onMovieSelected(movie: Movie?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DATA_ENTITY, MOVIE)
        intent.putExtra(EXTRA_DATA, movie)
        startActivity(intent)
    }

}