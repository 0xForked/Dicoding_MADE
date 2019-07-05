package id.aasumitro.made.data

import android.annotation.SuppressLint
import android.util.Log
import id.aasumitro.made.DamovieApp
import id.aasumitro.made.data.entity.DataResult
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.data.source.remote.ApiClient
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

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

    fun getMoviesByName(query: String, year: Int?):
            Observable<DataResult.MovieResult>? =
        apiClient.apiServices()?.getMoviesByName(query = query, year = year)

    fun getShowsByName(query: String, year: Int?):
            Observable<DataResult.ShowResult>? =
        apiClient.apiServices()?.getShowsByName(query = query, year = year)

    fun getUpcomingMovie():
            Observable<DataResult.MovieResult>? =
        apiClient.apiServices()?.getUpcomingMovie()

    @SuppressLint("CheckResult")
    fun storeFavoriteMovie(movie: Movie?) {
        Observable
            .fromCallable {
                DamovieApp()
                    .instanceDb()
                    .damovieDao()
                    .insertFavoriteMovie(movie as Movie)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d(
                    "insertFavoriteMovie",
                    "Mark event ${movie?.id} " +
                            "as favorite..."
                )
            }
    }

    @SuppressLint("CheckResult")
    fun deleteFavoriteMovie(movieId: Int?) {
        Observable
            .fromCallable {
                DamovieApp()
                    .instanceDb()
                    .damovieDao()
                    .deleteFavoriteMovie(movieId as Int)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("deleteFavoriteMovie", "Favorite movie")
            }
    }

    fun getFavoriteMovie(): Maybe<List<Movie>>? =
        DamovieApp()
            .instanceDb()
            .damovieDao()
            .readFavoriteMovie()
            .doOnSuccess {
                Log.d(
                    "getFavoriteMovie",
                    "get ${it.size} " +
                            "favorite data..."
                )
            }

    fun getMovieFavoriteState(movieId: Int?) =
        DamovieApp()
            .instanceDb()
            .damovieDao()
            .readMovieFavoriteState(movieId as Int)

    @SuppressLint("CheckResult")
    fun storeFavoriteShow(show: Show?) {
        Observable
            .fromCallable {
                DamovieApp()
                    .instanceDb()
                    .damovieDao()
                    .insertFavoriteShow(show as Show)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d(
                    "insertFavoriteShow",
                    "Mark event ${show?.id} " +
                            "as favorite..."
                )
            }
    }

    @SuppressLint("CheckResult")
    fun deleteFavoriteShow(showId: Int?) {
        Observable
            .fromCallable {
                DamovieApp()
                    .instanceDb()
                    .damovieDao()
                    .deleteFavoriteShow(showId as Int)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe {
                Log.d("deleteFavoriteShow", "Favorite movie")
            }
    }

    fun getFavoriteShow(): Maybe<List<Show>>? =
        DamovieApp()
            .instanceDb()
            .damovieDao()
            .readFavoriteShow()
            .doOnSuccess {
                Log.d(
                    "getFavoriteShow",
                    "get ${it.size} " +
                            "favorite data..."
                )
            }

    fun getShowFavoriteState(showId: Int?) =
        DamovieApp()
            .instanceDb()
            .damovieDao()
            .readShowFavoriteState(showId as Int)

}