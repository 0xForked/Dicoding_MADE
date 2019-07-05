package id.aasumitro.made.ui.main.favorite

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.ui.detail.DetailActivity
import id.aasumitro.made.ui.detail.DetailActivity.Companion.DATA_ENTITY
import id.aasumitro.made.ui.detail.DetailActivity.Companion.EXTRA_DATA
import id.aasumitro.made.ui.detail.DetailActivity.Companion.MOVIE
import id.aasumitro.made.ui.detail.DetailActivity.Companion.SHOW
import id.aasumitro.made.ui.rv.adapter.MovieAdapter
import id.aasumitro.made.ui.rv.adapter.ShowAdapter
import id.aasumitro.made.ui.rv.listener.MovieListener
import id.aasumitro.made.ui.rv.listener.ShowListener
import kotlinx.android.synthetic.main.fragment_favorite.*


/**
 * Created by A. A. Sumitro on 01/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class FavoriteFragment : BottomSheetDialogFragment(), MovieListener, ShowListener {

    companion object {
        val TAG = FavoriteFragment::class.java.simpleName
        fun newInstance(): FavoriteFragment =
            FavoriteFragment().apply {
                /*
                    arguments = Bundle().apply {
                        putInt(ARG_ITEM_COUNT, itemCount)
                    }
                 */
            }
    }

    private val mViewModel: FavoriteViewModel by lazy {
        ViewModelProviders
            .of(activity as FragmentActivity)
            .get(FavoriteViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_favorite,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMovieRv()
        initShowRv()
        onClickListener()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setOnShowListener {
            val d = it as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout?
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
        }

        return dialog
    }

    private fun initMovieRv() {
        val layoutManagerMovie: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_favorite_movie.setHasFixedSize(true)
        rv_favorite_movie.layoutManager = layoutManagerMovie
        rv_favorite_movie.itemAnimator = DefaultItemAnimator()
        mViewModel.movies().observe(this, Observer {
            rv_favorite_movie.adapter = MovieAdapter(it, this, true)
            if (it.isEmpty()) {
                rv_favorite_movie.visibility = View.GONE
                empty_movie.visibility = View.VISIBLE
            } else {
                rv_favorite_movie.visibility = View.VISIBLE
                empty_movie.visibility = View.GONE
            }
        })
    }

    private fun initShowRv() {
        val layoutManagerShow: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_favorite_show.setHasFixedSize(true)
        rv_favorite_show.layoutManager = layoutManagerShow
        rv_favorite_show.itemAnimator = DefaultItemAnimator()
        mViewModel.shows().observe(this, Observer {
            rv_favorite_show.adapter = ShowAdapter(it, this, true)
            if (it.isEmpty()) {
                rv_favorite_show.visibility = View.GONE
                empty_show.visibility = View.VISIBLE
            } else {
                rv_favorite_show.visibility = View.VISIBLE
                empty_show.visibility = View.GONE
            }
        })
    }

    private fun onClickListener() {
        close_button.setOnClickListener {
            this@FavoriteFragment.dismiss()
        }
    }

    override fun onMovieSelected(movie: Movie?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DATA_ENTITY, MOVIE)
        intent.putExtra(EXTRA_DATA, movie)
        startActivity(intent)
        this@FavoriteFragment.dismiss()
    }

    override fun onShowSelected(show: Show?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DATA_ENTITY, SHOW)
        intent.putExtra(EXTRA_DATA, show)
        startActivity(intent)
        this@FavoriteFragment.dismiss()
    }

}