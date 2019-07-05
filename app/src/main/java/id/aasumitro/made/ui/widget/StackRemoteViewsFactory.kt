package id.aasumitro.made.ui.widget

import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bumptech.glide.Glide
import id.aasumitro.made.DamovieApp
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.source.remote.ApiConst
import id.aasumitro.made.ui.widget.FavoriteWidget.Companion.EXTRA_ITEM


/**
 * Created by A. A. Sumitro on 03/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class StackRemoteViewsFactory(
    private val mContext: Context
) : RemoteViewsService.RemoteViewsFactory {

    private lateinit var mMovies: ArrayList<Movie>

    override fun onCreate() {}

    override fun getViewAt(position: Int): RemoteViews? {
        return RemoteViews(mContext.packageName, R.layout.widget_item).apply {
            try {
                val bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(ApiConst.IMAGE_URL + ApiConst.POSTER_SIZE + mMovies[position].backdropPath)
                    .submit(1024, 1024)
                    .get()
                setImageViewBitmap(R.id.tv_movie_backdrop, bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            setTextViewText(R.id.tv_movie_title, mMovies[position].title)

            val fillInIntent = Intent().apply {
                Bundle().also { extras ->
                    extras.putInt(EXTRA_ITEM, position)
                    putExtras(extras)
                }
            }

            // Make it possible to distinguish the individual on-click
            // action of a given item
            setOnClickFillInIntent(R.id.tv_movie_title, fillInIntent)
        }

    }

    override fun onDataSetChanged() {
        val identityToken: Long = Binder.clearCallingIdentity()
        mMovies = DamovieApp()
            .instanceDb()
            .damovieDao()
            .readFavorite() as ArrayList<Movie>
        Binder.restoreCallingIdentity(identityToken)
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(position: Int): Long =
        if (mMovies[position].id != null) mMovies[position].id as Long
        else position.toLong()

    override fun getCount(): Int = mMovies.size

    override fun hasStableIds(): Boolean = false

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() = mMovies.clear()

}
