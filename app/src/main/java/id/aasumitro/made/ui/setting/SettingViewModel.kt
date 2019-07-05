package id.aasumitro.made.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.aasumitro.made.data.Repository
import id.aasumitro.made.data.entity.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by A. A. Sumitro on 03/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class SettingViewModel : ViewModel() {

    private val mRepository: Repository = Repository()
    private var mUpcomingListMovies: MutableLiveData<ArrayList<Movie>>? = null

    fun upcomingMovie(): LiveData<ArrayList<Movie>> {
        mUpcomingListMovies = MutableLiveData()
        getUpcomingMovie()
        return mUpcomingListMovies as MutableLiveData
    }

    private fun getUpcomingMovie() {
        mRepository.let {
            it.getUpcomingMovie()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ onSuccess ->
                    mUpcomingListMovies?.value = onSuccess.results as ArrayList<Movie>
                }, { onError ->
                    onError.printStackTrace()
                })
        }
    }

}