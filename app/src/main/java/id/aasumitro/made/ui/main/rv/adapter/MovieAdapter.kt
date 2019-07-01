package id.aasumitro.made.ui.main.rv.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.ui.main.rv.holder.MovieViewHolder
import id.aasumitro.made.ui.main.rv.listener.MovieListener

/**
 * Created by A. A. Sumitro on 18/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MovieAdapter (
    private val data: ArrayList<Movie>,
    private val listener: MovieListener,
    private val isFavorite: Boolean = false
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val mView = when(isFavorite) {
            true ->  LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_favorite_placeholder, parent, false)
            false ->  LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_placeholder, parent, false)
        }
        return MovieViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(data[position], listener, isFavorite)

    override fun getItemCount(): Int = data.count()

}
