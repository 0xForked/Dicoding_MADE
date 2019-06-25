package id.aasumitro.made.ui.main.show

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.aasumitro.made.data.Repository
import id.aasumitro.made.data.entity.Show
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import androidx.lifecycle.MutableLiveData

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class ShowViewModel : ViewModel() {

    private val mRepository: Repository = Repository()
    private var mListTvShow: MutableLiveData<ArrayList<Show>>? = null

    fun shows() : LiveData<ArrayList<Show>> {
        if (mListTvShow == null) {
            mListTvShow = MutableLiveData()
            getShows()
        }

        return mListTvShow as MutableLiveData
    }

    private fun getShows() {
        mRepository.let {
            it.getShows()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({ onSuccess ->
                    mListTvShow?.value = onSuccess.results as ArrayList<Show>
                }, { onError ->
                    Log.d("ERROR", "ERROR BRO")
                    onError.printStackTrace()
                })
        }
    }

}
