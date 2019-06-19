package id.aasumitro.made.show

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@Parcelize
data class Show(
    var poster: Int = 0,
    var title: String? = null,
    var overview: String? = null,
    var rating: String? = null
) : Parcelable
