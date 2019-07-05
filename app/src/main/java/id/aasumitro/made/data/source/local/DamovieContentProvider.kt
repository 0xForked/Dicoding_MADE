package id.aasumitro.made.data.source.local

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.net.Uri

/**
 * Created by A. A. Sumitro on 04/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class DamovieContentProvider : ContentProvider() {

    private lateinit var mDamovie: DamovieDb

    override fun onCreate(): Boolean {
        mDamovie = DamovieDb.getInstance(context as Context)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val mContext: Context =
            context?.applicationContext as Context
        mContext.let {
            val movie =
                mDamovie
                    .damovieDao()
                    .readFavoriteCursor()
            movie.setNotificationUri(it.applicationContext.contentResolver, uri)
            return movie
        }
    }

    override fun insert(
        uri: Uri,
        values: ContentValues?
    ): Uri? = throw IllegalArgumentException("BLOCK")

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = throw IllegalArgumentException("BLOCK")

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = throw IllegalArgumentException("BLOCK")

    override fun getType(
        uri: Uri
    ): String? = null

}