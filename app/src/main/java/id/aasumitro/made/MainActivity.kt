package id.aasumitro.made

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.content.Intent
import android.view.MenuItem
import android.provider.Settings.ACTION_LOCALE_SETTINGS

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager, this)
        main_view_pager.adapter = mViewPagerAdapter
        main_tab_layout.setupWithViewPager(main_view_pager)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_language) {
            val mIntent = Intent(ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
