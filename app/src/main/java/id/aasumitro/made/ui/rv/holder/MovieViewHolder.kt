package id.aasumitro.made.ui.rv.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.source.remote.ApiConst.IMAGE_URL
import id.aasumitro.made.data.source.remote.ApiConst.POSTER_SIZE
import id.aasumitro.made.ui.rv.listener.MovieListener
import id.aasumitro.made.utils.GlideApp
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
        val requestOptions = RequestOptions()
        requestOptions.apply {
            placeholder(R.drawable.ic_cloud_download_gray_24dp)
            error(R.drawable.ic_broken_image_gray_24dp)
            centerCrop()
        }

        if (isFavorite) {
            GlideApp.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(IMAGE_URL + POSTER_SIZE + movie.posterPath)
                .into(item_favorite_poster)
            item_favorite_title.text = movie.title
            item_favorite_container.setOnClickListener { listener.onMovieSelected(movie) }
        } else {
            GlideApp.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(IMAGE_URL + POSTER_SIZE + movie.posterPath)
                .into(item_list_poster)
            item_list_title.text = movie.title
            item_list_container.setOnClickListener { listener.onMovieSelected(movie) }
        }

    }

}
