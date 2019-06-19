package id.aasumitro.made.movie

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*

/**
 * Created by A. A. Sumitro on 18/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */


class MovieViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(
        movie: Movie,
        listener: MovieListener
    ) {
        itemView.item_movie_poster.setImageResource(movie.poster)
        itemView.item_movie_title.text = movie.title
        val rating = "Rating: ${movie.rating}"
        itemView.item_movie_rating.text = rating
        itemView.item_movie_container.setOnClickListener {
            listener.onMovieSelected(movie)
        }
    }

}
