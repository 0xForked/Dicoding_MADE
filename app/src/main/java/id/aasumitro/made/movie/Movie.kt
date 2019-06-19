package id.aasumitro.made.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by A. A. Sumitro on 18/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@Parcelize
data class Movie(
    var poster: Int = 0,
    var title: String? = null,
    var overview: String? = null,
    var rating: String? = null
) : Parcelable
