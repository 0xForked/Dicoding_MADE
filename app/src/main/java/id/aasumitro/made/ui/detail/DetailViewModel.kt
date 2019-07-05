package id.aasumitro.made.ui.detail

import androidx.lifecycle.ViewModel
import id.aasumitro.made.data.Repository
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.ui.detail.DetailActivity.Companion.MOVIE
import id.aasumitro.made.ui.detail.DetailActivity.Companion.SHOW

/**
 * Created by A. A. Sumitro on 01/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class DetailViewModel : ViewModel() {

    private val mRepository: Repository = Repository()

    private var mFavoriteEvent: Boolean = false

    fun markAsFavorite(
        type: String,
        movie: Movie? = null,
        show: Show? = null
    ) {
        when (type) {
            MOVIE -> mRepository.storeFavoriteMovie(movie)
            SHOW -> mRepository.storeFavoriteShow(show)
        }
    }

    fun removeFromFavorite(
        type: String,
        id: Int?
    ) {
        when (type) {
            MOVIE -> mRepository.deleteFavoriteMovie(id)
            SHOW -> mRepository.deleteFavoriteShow(id)
        }
    }

    fun favoriteState(
        type: String,
        id: Int?
    ) {
        val favorite = when (type) {
            MOVIE -> mRepository.getMovieFavoriteState(id)
            SHOW -> mRepository.getShowFavoriteState(id)
            else -> null
        }

        if (favorite !== null) {
            favorite.let {
                mFavoriteEvent = it.isNotEmpty()
            }
        }
    }

    fun getFavoriteState(): Boolean = mFavoriteEvent

}