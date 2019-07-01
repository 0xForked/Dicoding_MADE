package id.aasumitro.made.ui.main.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.ui.main.rv.holder.ShowViewHolder
import id.aasumitro.made.ui.main.rv.listener.ShowListener

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class ShowAdapter(
    private val data: ArrayList<Show>,
    private val listener: ShowListener,
    private val isFavorite: Boolean = false
) : RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowViewHolder {
        val mView = when(isFavorite) {
            true ->  LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_favorite_placeholder, parent, false)
            false ->  LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_placeholder, parent, false)
        }
        return ShowViewHolder(mView)
    }

    override fun onBindViewHolder(
        holder: ShowViewHolder,
        position: Int
    ) = holder.bind(data[position], listener, isFavorite)

    override fun getItemCount(): Int = data.count()

}