package id.aasumitro.made

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.aasumitro.made.movie.Movie
import id.aasumitro.made.show.Show
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_ENTITY = "DATA_ENTITY"
        const val EXTRA_DATA = "EXTRA_DATA"
        const val MOVIE = "MOVIE"
        const val SHOW = "SHOW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val data = intent.getStringExtra(DATA_ENTITY)

        if (data == MOVIE) {
            val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
            val rating = "Rating: ${movie.rating}"
            movie_poster_detail.setImageResource(movie.poster)
            movie_title_detail.text = movie.title
            movie_rating_detail.text = rating
            movie_overview_detail.text = movie.overview
        } else {
            val show = intent.getParcelableExtra<Show>(EXTRA_DATA)
            val rating = "Rating: ${show.rating}"
            movie_poster_detail.setImageResource(show.poster)
            movie_title_detail.text = show.title
            movie_rating_detail.text = rating
            movie_overview_detail.text = show.overview
        }

    }

}
