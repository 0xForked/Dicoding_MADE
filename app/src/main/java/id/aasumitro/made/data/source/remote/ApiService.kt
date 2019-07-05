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

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") dataLanguage: String = DATA_LANG
    ): Observable<DataResult.MovieResult>

    @GET("discover/tv")
    fun getShow(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") dataLanguage: String = DATA_LANG
    ): Observable<DataResult.ShowResult>

    @GET("search/movie")
    fun getMoviesByName(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") dataLanguage: String = DATA_LANG,
        @Query("query") query: String? = null,
        @Query("year") year: Int? = null
    ): Observable<DataResult.MovieResult>

    @GET("search/tv")
    fun getShowsByName(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") dataLanguage: String = DATA_LANG,
        @Query("query") query: String? = null,
        @Query("first_air_date_year") year: Int? = null
    ): Observable<DataResult.ShowResult>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey: String = API_KEY
    ): Observable<DataResult.MovieResult>

}