package id.aasumitro.made_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.res.TypedArray
import id.aasumitro.made_app.DetailActivity.Companion.EXTRA_DATA

class MainActivity : AppCompatActivity(), MovieListener {

    private var mPoster: TypedArray? = null
    private var mTitle: Array<String>? = null
    private var mOverview: Array<String>? = null
    private var mRating: Array<String>? = null
    private var mAdapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()
        addMovie()
        initView()
    }

    private fun initData() {
        mPoster = resources.obtainTypedArray(R.array.data_poster)
        mTitle = resources.getStringArray(R.array.data_title)
        mOverview = resources.getStringArray(R.array.data_overview)
        mRating = resources.getStringArray(R.array.data_rating)
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
        mAdapter = MovieAdapter(this, this, mMovies)
    }

    private fun initView() {
        main_movie_list_view.adapter = mAdapter
    }

    override fun onMovieSelected(movie: Movie?) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_DATA, movie)
        startActivity(intent)
    }

}
