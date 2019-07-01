package id.aasumitro.made.ui.main.rv.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.source.remote.ApiConst
import id.aasumitro.made.ui.main.rv.listener.MovieListener
import kotlinx.android.synthetic.main.item_list_favorite_placeholder.view.*
import kotlinx.android.synthetic.main.item_list_placeholder.view.*

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
        listener: MovieListener,
        isFavorite: Boolean
    ) = with(itemView) {
        if (isFavorite) {
            Picasso.get()
                .load(ApiConst.IMAGE_URL + ApiConst.POSTER_SIZE + movie.posterPath)
                .placeholder(R.drawable.ic_cloud_download_gray_24dp)
                .error(R.drawable.ic_broken_image_gray_24dp)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(item_favorite_poster)
            item_favorite_title.text = movie.title
            item_favorite_container.setOnClickListener {
                listener.onMovieSelected(movie)
            }
        } else {
            Picasso.get()
                .load(ApiConst.IMAGE_URL + ApiConst.POSTER_SIZE + movie.posterPath)
                .placeholder(R.drawable.ic_cloud_download_gray_24dp)
                .error(R.drawable.ic_broken_image_gray_24dp)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(item_list_poster)
            item_list_title.text = movie.title
            item_list_container.setOnClickListener {
                listener.onMovieSelected(movie)
            }
        }

    }

}
