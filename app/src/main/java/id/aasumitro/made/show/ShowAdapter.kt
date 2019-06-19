package id.aasumitro.made.show

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.R

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class ShowAdapter(
    private val data: ArrayList<Show>,
    private val listener: ShowListener
) : RecyclerView.Adapter<ShowViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_show, parent, false)
        return ShowViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ShowViewHolder,
        position: Int
    ) = holder.bind(data[position], listener)

    override fun getItemCount(): Int = data.count()

}