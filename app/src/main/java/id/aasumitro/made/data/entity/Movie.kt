package id.aasumitro.made.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_ADULT
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_BACKDROP
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_ID
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_ORI_LANG
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_ORI_TITLE
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_OVERVIEW
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_POPULARITY
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_POSTER
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_RELEASE
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_TITLE
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_VIDEO
import id.aasumitro.made.data.source.local.DbConst.TABLE_MOVIE_COLUMN_VOTE
import kotlinx.android.parcel.Parcelize

/**
 * Created by A. A. Sumitro on 18/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@Parcelize
@Entity(tableName = TABLE_MOVIE)
data class Movie(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = TABLE_MOVIE_COLUMN_ID)
    @SerializedName("id")
    var id: Long? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_VOTE)
    @SerializedName("vote_count")
    var voteCount: Long? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_VIDEO)
    @SerializedName("video")
    var video: Boolean? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_TITLE)
    @SerializedName("title")
    var title: String? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_POPULARITY)
    @SerializedName("popularity")
    var popularity: Float? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_POSTER)
    @SerializedName("poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_ORI_LANG)
    @SerializedName("originalLanguage")
    var originalLanguage: String? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_ORI_TITLE)
    @SerializedName("original_title")
    var originalTitle: String? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_BACKDROP)
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_ADULT)
    @SerializedName("adult")
    var adult: Boolean? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_OVERVIEW)
    @SerializedName("overview")
    var overview: String? = null,

    @ColumnInfo(name = TABLE_MOVIE_COLUMN_RELEASE)
    @SerializedName("release_date")
    var releaseDate: String? = null
) : Parcelable
