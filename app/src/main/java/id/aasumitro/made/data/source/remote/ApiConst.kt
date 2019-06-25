package id.aasumitro.made.data.source.remote

import android.content.res.Resources
import android.os.Build

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

object ApiConst {

    const val BASE_URL = "https://api.themoviedb.org/3/discover/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/"
    const val POSTER_SIZE = "w185/"
    const val BACK_DROP_SiZE = "w400/"
    const val API_KEY = "cb94a868460fa0efb516ab8887003f50"

    @Suppress("DEPRECATION")
    val DATA_LANG =  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        Resources.getSystem().configuration.locales.get(0).toString() else
        Resources.getSystem().configuration.locale.toString()

}

