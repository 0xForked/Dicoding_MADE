package id.aasumitro.made.ui.main.rv.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.data.source.remote.ApiConst.IMAGE_URL
import id.aasumitro.made.data.source.remote.ApiConst.POSTER_SIZE
import id.aasumitro.made.ui.main.rv.listener.ShowListener
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
        if (isFavorite) {
            Picasso.get()
                .load(IMAGE_URL + POSTER_SIZE + show.posterPath)
                .placeholder(R.drawable.ic_cloud_download_gray_24dp)
                .error(R.drawable.ic_broken_image_gray_24dp)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(item_favorite_poster)
            item_favorite_title.text = show.name
            item_favorite_container.setOnClickListener {
                listener.onShowSelected(show)
            }
        } else {
            Picasso.get()
                .load(IMAGE_URL + POSTER_SIZE + show.posterPath)
                .placeholder(R.drawable.ic_cloud_download_gray_24dp)
                .error(R.drawable.ic_broken_image_gray_24dp)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(item_list_poster)
            item_list_title.text = show.name
            item_list_container.setOnClickListener {
                listener.onShowSelected(show)
            }
        }

    }

}
