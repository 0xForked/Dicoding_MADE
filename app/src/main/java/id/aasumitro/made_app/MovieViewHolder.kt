package id.aasumitro.made_app

import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by A. A. Sumitro on 18/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MovieViewHolder(
    private val mView: View
) {
    private lateinit var mLayout: RelativeLayout
    private lateinit var mPoster: ImageView
    private lateinit var mTitle: TextView
    private lateinit var mRating: TextView

    fun bind(movie: Movie, listener: MovieListener) {
        mLayout = mView.findViewById(R.id.item_movie_container)
        mPoster = mView.findViewById(R.id.item_movie_poster)
        mTitle = mView.findViewById(R.id.item_movie_title)
        mRating = mView.findViewById(R.id.item_movie_rating)

        mPoster.setImageResource(movie.poster)
        mTitle.text = movie.title
        val rating = "Rating: ${movie.rating}"
        mRating.text = rating
        mLayout.setOnClickListener {
            listener.onMovieSelected(movie)
        }
    }
}
