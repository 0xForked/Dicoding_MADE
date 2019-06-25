package id.aasumitro.made.data.source.remote

import id.aasumitro.made.data.entity.DataResult
import id.aasumitro.made.data.source.remote.ApiConst.API_KEY
import id.aasumitro.made.data.source.remote.ApiConst.DATA_LANG
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

interface ApiService {

    @GET("movie")
    fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") dataLanguage: String = DATA_LANG

    ): Observable<DataResult.MovieResult>

    @GET("tv")
    fun getShow(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") dataLanguage: String = DATA_LANG
    ): Observable<DataResult.ShowResult>

}