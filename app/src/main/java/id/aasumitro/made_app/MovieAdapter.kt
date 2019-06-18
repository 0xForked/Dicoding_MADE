package id.aasumitro.made_app

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Created by A. A. Sumitro on 18/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MovieAdapter(
    private val mContext: Context,
    private val listener: MovieListener,
    private val mMovies: ArrayList<Movie>
) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(
        position: Int,
        view: View?,
        parent: ViewGroup?
    ): View {
        val inflater: LayoutInflater =
            mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            ) as LayoutInflater
        val mView = inflater.inflate(
            R.layout.item_movie,
            parent,
            false
        )
        val movies = getItem(position) as Movie
        val viewHolder = MovieViewHolder(mView)
        viewHolder.bind(movies, listener)
        return mView
    }

    override fun getItem(position: Int): Any = mMovies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = mMovies.size

}