package id.aasumitro.made.data

import id.aasumitro.made.data.entity.DataResult
import id.aasumitro.made.data.source.remote.ApiClient
import io.reactivex.Observable

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */


class Repository(
    private val apiClient: ApiClient = ApiClient()
) {

    fun getMovies():
            Observable<DataResult.MovieResult>? =
                apiClient.apiServices()?.getMovies()

    fun getShows():
            Observable<DataResult.ShowResult>? =
                apiClient.apiServices()?.getShow()

}