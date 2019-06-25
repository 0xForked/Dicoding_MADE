package id.aasumitro.made.ui.main.show

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.data.source.remote.ApiConst.IMAGE_URL
import id.aasumitro.made.data.source.remote.ApiConst.POSTER_SIZE
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
        listener: ShowListener
    ) {
        Picasso.get()
            .load(IMAGE_URL + POSTER_SIZE + show.posterPath)
            .placeholder(R.drawable.ic_cloud_download_gray_24dp)
            .error(R.drawable.ic_broken_image_gray_24dp)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(itemView.item_show_poster)
        itemView.item_show_ratting.text = show.name
        itemView.item_show_container.setOnClickListener {
            listener.onShowSelected(show)
        }
    }

}
