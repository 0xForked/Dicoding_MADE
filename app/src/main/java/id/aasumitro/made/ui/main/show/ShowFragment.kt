package id.aasumitro.made.ui.main.show

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.aasumitro.made.R
import id.aasumitro.made.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_show.*
import id.aasumitro.made.data.entity.Show
import id.aasumitro.made.ui.detail.DetailActivity
import id.aasumitro.made.ui.detail.DetailActivity.Companion.DATA_ENTITY
import id.aasumitro.made.ui.detail.DetailActivity.Companion.EXTRA_DATA
import id.aasumitro.made.ui.detail.DetailActivity.Companion.SHOW

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class ShowFragment : BaseFragment(R.layout.fragment_show), ShowListener {

    private val mViewModel: ShowViewModel by lazy {
        ViewModelProviders
            .of(activity as FragmentActivity)
            .get(ShowViewModel::class.java)
    }

    override fun initView() { }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
    }

    private fun initList() {
        show_recycler_view.setHasFixedSize(true)
        val mLayoutManager : RecyclerView.LayoutManager =
            GridLayoutManager(activity, 2)
        show_recycler_view.layoutManager = mLayoutManager
        show_recycler_view.itemAnimator = DefaultItemAnimator()
        mViewModel.shows().observe(this, Observer {
            show_recycler_view.adapter = ShowAdapter(it, this)
        })
    }

    override fun onShowSelected(show: Show?) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(DATA_ENTITY, SHOW)
        intent.putExtra(EXTRA_DATA, show)
        startActivity(intent)
    }

}