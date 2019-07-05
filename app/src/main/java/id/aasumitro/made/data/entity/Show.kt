package id.aasumitro.made.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_BACKDROP
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_FIRST_AIR
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_ID
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_NAME
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_ORI_LANG
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_ORI_NAME
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_OVERVIEW
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_POPULARITY
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_POSTER
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_VOTE_AVG
import id.aasumitro.made.data.source.local.DbConst.TABLE_SHOW_COLUMN_VOTE_COUNT
import kotlinx.android.parcel.Parcelize

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

@Parcelize
@Entity(tableName = TABLE_SHOW)
data class Show(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = TABLE_SHOW_COLUMN_ID)
    @SerializedName("id")
    var id: Long? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_ORI_NAME)
    @SerializedName("original_name")
    var originalName: String? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_NAME)
    @SerializedName("name")
    var name: String? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_POPULARITY)
    @SerializedName("popularity")
    var popularity: Float? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_VOTE_COUNT)
    @SerializedName("vote_count")
    var voteCount: Long? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_FIRST_AIR)
    @SerializedName("first_air_date")
    var firstAirDate: String? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_BACKDROP)
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_ORI_LANG)
    @SerializedName("originalLanguage")
    var originalLanguage: String? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_VOTE_AVG)
    @SerializedName("vote_average")
    var voteAverage: Float? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_OVERVIEW)
    @SerializedName("overview")
    var overview: String? = null,

    @ColumnInfo(name = TABLE_SHOW_COLUMN_POSTER)
    @SerializedName("poster_path")
    var posterPath: String? = null
) : Parcelable
