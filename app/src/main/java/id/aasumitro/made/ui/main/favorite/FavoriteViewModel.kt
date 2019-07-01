package id.aasumitro.made.ui.main.favorite

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.aasumitro.made.data.Repository
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by A. A. Sumitro on 01/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class FavoriteViewModel : ViewModel() {

    private val mRepository: Repository = Repository()
    private var mListMovies: MutableLiveData<ArrayList<Movie>>? = null
    private var mListShows: MutableLiveData<ArrayList<Show>>? = null

    fun movies() : LiveData<ArrayList<Movie>> {
        mListMovies = MutableLiveData()
        getMovies()
        return mListMovies as MutableLiveData
    }

    fun shows() : LiveData<ArrayList<Show>> {
        mListShows = MutableLiveData()
        getShows()
        return mListShows as MutableLiveData
    }


    @SuppressLint("CheckResult")
    private fun getMovies() {
        mRepository
            .getFavoriteMovie()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ onSuccess ->
                mListMovies?.value = onSuccess as ArrayList<Movie>
                Log.d("MOVIE", onSuccess.toString())
            }, { onError ->
                onError.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    private fun getShows() {
        mRepository
            .getFavoriteShow()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe({ onSuccess ->
                mListShows?.value = onSuccess as ArrayList<Show>
                Log.d("SHOW", onSuccess.toString())

            }, { onError ->
                onError.printStackTrace()
            })
    }

}