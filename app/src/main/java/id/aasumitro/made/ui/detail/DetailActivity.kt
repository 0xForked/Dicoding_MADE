package id.aasumitro.made.ui.detail

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.request.RequestOptions
import id.aasumitro.made.R
import id.aasumitro.made.base.BaseActivity
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.data.source.remote.ApiConst.BACK_DROP_SiZE
import id.aasumitro.made.data.source.remote.ApiConst.IMAGE_URL
import id.aasumitro.made.data.source.remote.ApiConst.POSTER_SIZE
import id.aasumitro.made.ui.widget.FavoriteWidget
import id.aasumitro.made.utils.GlideApp
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*



class DetailActivity : BaseActivity(R.layout.activity_detail) {

    companion object {
        const val DATA_ENTITY = "DATA_ENTITY"
        const val EXTRA_DATA = "EXTRA_DATA"
        const val MOVIE = "MOVIE"
        const val SHOW = "SHOW"
        const val ALL = "ALL"
    }

    private var mMenuItem: Menu? = null

    private var isFavorite: Boolean = false

    private lateinit var mDetailType: String
    private lateinit var mMovie: Movie
    private lateinit var mShow: Show

    private val mViewModel: DetailViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(DetailViewModel::class.java)
    }

    override fun initView() {
        initToolbar(pageName = "DA'MOVIE - DETAIL", back = true)
        val data = intent.getStringExtra(DATA_ENTITY)

        if (data == MOVIE) {
            mDetailType = MOVIE
            val movie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
            mMovie = movie

            val mapData = mapOf(
                "backdropPath" to movie.backdropPath,
                "posterPath" to movie.posterPath,
                "title" to movie.title,
                "first_air_date" to movie.releaseDate,
                "popularity" to movie.popularity.toString(),
                "overview" to movie.overview
            )
            setData(mapData)
        }

        if (data == SHOW) {
            mDetailType = SHOW
            val show = intent.getParcelableExtra<Show>(EXTRA_DATA)
            mShow = show

            val mapData = mapOf(
                "backdropPath" to show.backdropPath,
                "posterPath" to show.posterPath,
                "title" to show.name,
                "first_air_date" to show.firstAirDate,
                "popularity" to show.popularity.toString(),
                "overview" to show.overview
            )
            setData(mapData)

        }

        favoriteState()

    }

    private fun setData(
        mapData: Map<String, String?>
    ) {
        val requestOptions = RequestOptions()
        requestOptions.apply {
            placeholder(R.drawable.ic_cloud_download_gray_24dp)
            error(R.drawable.ic_broken_image_gray_24dp)
            centerCrop()
        }

        GlideApp.with(this)
            .setDefaultRequestOptions(requestOptions)
            .load(IMAGE_URL + BACK_DROP_SiZE + mapData["backdropPath"])
            .into(poster_backdrop_detail)

        GlideApp.with(this)
            .setDefaultRequestOptions(requestOptions)
            .load(IMAGE_URL + POSTER_SIZE + mapData["posterPath"])
            .into(poster_detail)

        title_detail.text = mapData["title"]
        text_first_air_display.text = mapData["first_air_date"]
        text_popularity_display.text = mapData["popularity"]
        text_overview_display.text = mapData["overview"]
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        mMenuItem = menu
        favoriteStatus()
        //menu?.findItem(R.id.menu_favorite)?.isVisible = isLoadingNow
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_favorite -> {
                if (isFavorite) {
                    when (mDetailType) {
                        MOVIE -> mViewModel.removeFromFavorite(MOVIE, mMovie.id?.toInt())
                        SHOW -> mViewModel.removeFromFavorite(SHOW, mShow.id?.toInt())
                    }
                } else {
                    when (mDetailType) {
                        MOVIE -> mViewModel.markAsFavorite(MOVIE, mMovie, null)
                        SHOW -> mViewModel.markAsFavorite(SHOW, null, mShow)
                    }
                }
                isFavorite = !isFavorite
                favoriteStatus()

                // force refresh the widget service
                val mIntent = Intent(this, FavoriteWidget::class.java)
                mIntent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
                val ids = AppWidgetManager
                    .getInstance(this)
                    .getAppWidgetIds(
                        ComponentName(
                            this,
                            FavoriteWidget::class.java
                        ))
                mIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
                sendBroadcast(mIntent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        when (mDetailType) {
            MOVIE -> mViewModel.favoriteState(MOVIE, mMovie.id?.toInt())
            SHOW -> mViewModel.favoriteState(SHOW, mShow.id?.toInt())
        }
        isFavorite = mViewModel.getFavoriteState()
    }

    private fun favoriteStatus() {
        if (isFavorite) {
            mMenuItem?.getItem(0)?.icon = ContextCompat
                .getDrawable(this, R.drawable.ic_favorite_white_24dp)
        } else {
            mMenuItem?.getItem(0)?.icon = ContextCompat
                .getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
        }
    }

}
