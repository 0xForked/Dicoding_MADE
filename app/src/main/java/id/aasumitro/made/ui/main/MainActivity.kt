package id.aasumitro.made.ui.main

import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.content.Intent
import android.view.MenuItem
import android.provider.Settings.ACTION_LOCALE_SETTINGS
import id.aasumitro.made.R
import id.aasumitro.made.base.BaseActivity
import id.aasumitro.made.ui.main.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(R.layout.activity_main)  {

    override fun initView() {
        initToolbar(pageName = getString(R.string.app_title), back = false)
        val mViewPagerAdapter =
            MainViewPagerAdapter(supportFragmentManager, this)
        main_view_pager.adapter = mViewPagerAdapter
        main_tab_layout.setupWithViewPager(main_view_pager)
    }

    override fun initListener() {
        super.initListener()
        fab.setOnClickListener {
            val mFavorite =
                FavoriteFragment.newInstance()
            mFavorite.show(supportFragmentManager, FavoriteFragment.TAG)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_change_language) {
            val mIntent = Intent(ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
