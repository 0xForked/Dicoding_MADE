package id.aasumitro.made.show

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_show.view.*

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
        itemView.item_show_poster.setImageResource(show.poster)
        itemView.item_show_ratting.text = show.rating
        itemView.item_show_container.setOnClickListener {
            listener.onShowSelected(show)
        }
    }

}
