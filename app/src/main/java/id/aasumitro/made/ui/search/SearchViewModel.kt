package id.aasumitro.made.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.aasumitro.made.data.Repository
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by A. A. Sumitro on 02/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class SearchViewModel : ViewModel() {

    private val mRepository: Repository = Repository()

    private var mMovies: MutableLiveData<ArrayList<Movie>>? = null
    private var mShows: MutableLiveData<ArrayList<Show>>? = null

    var mLoadingState = MutableLiveData<Boolean>()
    var mErrorState = MutableLiveData<Boolean>()
    var mErrorMessage = MutableLiveData<String>()

    fun movies(query: String, year: Int?): LiveData<ArrayList<Movie>> {
        mMovies = MutableLiveData()
        searchMovies(query, year)
        return mMovies as MutableLiveData
    }

    fun shows(query: String, year: Int?): LiveData<ArrayList<Show>> {
        mShows = MutableLiveData()
        searchShows(query, year)
        return mShows as MutableLiveData
    }

    private fun searchMovies(query: String, year: Int?) {
        mLoadingState.postValue(true)
        mRepository.let {
            it.getMoviesByName(query, year)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ onSuccess ->
                    mMovies?.value = onSuccess.results as ArrayList<Movie>
                    mLoadingState.postValue(false)
                    mErrorState.postValue(false)
                }, { onError ->
                    onError.printStackTrace()
                    mLoadingState.postValue(false)
                    mErrorState.postValue(true)
                    mErrorMessage.postValue(onError.message)
                })
        }
    }

    private fun searchShows(query: String, year: Int?) {
        mLoadingState.postValue(true)
        mRepository.let {
            it.getShowsByName(query, year)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ onSuccess ->
                    mShows?.value = onSuccess.results as ArrayList<Show>
                    mLoadingState.postValue(false)
                    mErrorState.postValue(false)
                }, { onError ->
                    onError.printStackTrace()
                    mLoadingState.postValue(false)
                    mErrorState.postValue(true)
                    mErrorMessage.postValue(onError.message)
                })
        }
    }

}