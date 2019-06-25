package id.aasumitro.made.ui.main.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Movie

/**
 * Created by A. A. Sumitro on 18/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MovieAdapter (
    private val data: ArrayList<Movie>,
    private val listener: MovieListener
) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_placeholder, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(data[position], listener)

    override fun getItemCount(): Int = data.count()

}
