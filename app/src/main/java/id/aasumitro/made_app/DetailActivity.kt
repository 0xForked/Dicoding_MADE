package id.aasumitro.made_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)

        val rating = "Rating: ${movie.rating}"

        movie_poster_detail.setImageResource(movie.poster)
        movie_title_detail.text = movie.title
        movie_rating_detail.text = rating
        movie_overview_detail.text = movie.overview
    }

}
