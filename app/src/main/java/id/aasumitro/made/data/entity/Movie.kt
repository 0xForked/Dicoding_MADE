package id.aasumitro.made.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by A. A. Sumitro on 18/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@Parcelize
data class Movie(
    @SerializedName("id")
    var id: Long? = null,

    @SerializedName("vote_count")
    var voteCount: Long? = null,

    @SerializedName("video")
    var video: Boolean? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("popularity")
    var popularity: Float? = null,

    @SerializedName("poster_path")
    var posterPath: String? = null,

    @SerializedName("originalLanguage")
    var originalLanguage: String? = null,

    @SerializedName("original_title")
    var originalTitle: String? = null,

    @SerializedName("genre_ids")
    var genreIds: ArrayList<Int>? = null,

    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("overview")
    var overview: String? = null,

    @SerializedName("release_date")
    var releaseDate: String? = null
) : Parcelable
