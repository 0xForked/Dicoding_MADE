package id.aasumitro.made.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@Parcelize
data class Show(
    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("original_name")
    var originalName: String? = null,

    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int>? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("popularity")
    var popularity: Float? = null,

    @SerializedName("origin_country")
    var originCountry: ArrayList<String>? = null,

    @SerializedName("vote_count")
    var voteCount: Long? = null,

    @SerializedName("first_air_date")
    var firstAirDate: String? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String?= null,

    @SerializedName("originalLanguage")
    var originalLanguage: String? = null,

    @SerializedName("vote_average")
    var voteAverage: Float? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null
) : Parcelable
