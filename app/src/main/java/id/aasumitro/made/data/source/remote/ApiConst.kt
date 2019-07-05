package id.aasumitro.made.data.source.remote

import android.content.res.Resources
import android.os.Build
import id.aasumitro.made.BuildConfig

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

object ApiConst {

    const val BASE_URL = BuildConfig.API_URL
    const val IMAGE_URL = BuildConfig.THUMBNAIL_URL

    const val API_KEY = BuildConfig.API_KEY

    const val POSTER_SIZE = "w185/"
    const val BACK_DROP_SiZE = "w400/"

    @Suppress("DEPRECATION")
    val DATA_LANG = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Resources.getSystem().configuration.locales.get(0).toString() else
        Resources.getSystem().configuration.locale.toString()

}

