package id.aasumitro.made.show

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.R
import kotlinx.android.synthetic.main.fragment_show.*
import id.aasumitro.made.DetailActivity
import id.aasumitro.made.DetailActivity.Companion.DATA_ENTITY
import id.aasumitro.made.DetailActivity.Companion.EXTRA_DATA
import id.aasumitro.made.DetailActivity.Companion.SHOW

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class ShowFragment : Fragment(), ShowListener {

    private var mPoster: TypedArray? = null
    private var mTitle: Array<String>? = null
    private var mOverview: Array<String>? = null
    private var mRating: Array<String>? = null
    private var mAdapter: ShowAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_show,
        container,
        false
    )

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        addShow()
        initList()
    }

    private fun initData() {
        mPoster = resources.obtainTypedArray(R.array.data_show_poster)
        mTitle = resources.getStringArray(R.array.data_show_title)
        mOverview = resources.getStringArray(R.array.data_show_overview)
        mRating = resources.getStringArray(R.array.data_show_rating)
    }

    private fun addShow() {
        val mShows = ArrayList<Show>()
        for (i in 0 until mTitle?.size as Int) {
            val mShow = Show()
            mShow.poster = (mPoster?.getResourceId(i, -1) as Int)
            mShow.title = mTitle?.get(i)
            mShow.overview = mOverview?.get(i)
            mShow.rating = mRating?.get(i)
            mShows.add(mShow)
        }
        mAdapter = ShowAdapter(mShows, this)
    }

    private fun initList() {
        show_recycler_view.setHasFixedSize(true)
        val mLayoutManager : RecyclerView.LayoutManager =
            GridLayoutManager(activity, 2)
        show_recycler_view.layoutManager = mLayoutManager
        show_recycler_view.itemAnimator = DefaultItemAnimator()
        show_recycler_view.adapter = mAdapter
    }

    override fun onShowSelected(show: Show?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DATA_ENTITY, SHOW)
        intent.putExtra(EXTRA_DATA, show)
        startActivity(intent)
    }

}