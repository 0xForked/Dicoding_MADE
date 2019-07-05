package id.aasumitro.made.ui.rv.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.data.source.remote.ApiConst.IMAGE_URL
import id.aasumitro.made.data.source.remote.ApiConst.POSTER_SIZE
import id.aasumitro.made.ui.rv.listener.ShowListener
import id.aasumitro.made.utils.GlideApp
import kotlinx.android.synthetic.main.item_list_favorite_placeholder.view.*
import kotlinx.android.synthetic.main.item_list_placeholder.view.*

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class ShowViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {

    fun bind(
        show: Show,
        listener: ShowListener,
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
                .load(IMAGE_URL + POSTER_SIZE + show.posterPath)
                .into(item_favorite_poster)
            item_favorite_title.text = show.name
            item_favorite_container.setOnClickListener {
                listener.onShowSelected(show)
            }
        } else {
            GlideApp.with(this)
                .setDefaultRequestOptions(requestOptions)
                .load(IMAGE_URL + POSTER_SIZE + show.posterPath)
                .into(item_list_poster)
            item_list_title.text = show.name
            item_list_container.setOnClickListener {
                listener.onShowSelected(show)
            }
        }

    }

}
