package id.aasumitro.made.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.aasumitro.made.data.Repository
import id.aasumitro.made.data.entity.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MovieViewModel : ViewModel() {

    private val mRepository: Repository = Repository()
    private var mListMovies: MutableLiveData<ArrayList<Movie>>? = null
    private var mMovieNavigator: MovieNavigator? = null

    fun initVM(
        navigator: MovieNavigator
    ) {
        this.mMovieNavigator = navigator
    }

    fun movies(): LiveData<ArrayList<Movie>> {
        if (mListMovies == null) {
            mListMovies = MutableLiveData()
            getMovies()
        }
        return mListMovies as MutableLiveData
    }

    private fun getMovies() {
        this.mMovieNavigator?.onLoadData(true)
        mRepository.let {
            it.getMovies()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ onSuccess ->
                    mListMovies?.value = onSuccess.results as ArrayList<Movie>
                    this.mMovieNavigator?.onLoadData(false)
                }, { onError ->
                    onError.printStackTrace()
                    this.mMovieNavigator?.onErrorCallback(onError.message as String)
                })
        }
    }

}
