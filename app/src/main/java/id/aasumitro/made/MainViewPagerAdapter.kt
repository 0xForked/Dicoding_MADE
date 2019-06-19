package id.aasumitro.made

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import id.aasumitro.made.movie.MovieFragment
import id.aasumitro.made.show.ShowFragment

/**
 * Created by A. A. Sumitro on 19/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class MainViewPagerAdapter(
    fm: FragmentManager, context: Context
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragment = listOf(
        MovieFragment(),
        ShowFragment()
    )

    private val mFragmentTitle = listOf(
        context.getString(R.string.title_movie),
        context.getString(R.string.title_show)
    )

    override fun getItem(position: Int): Fragment = mFragment[position]

    override fun getPageTitle(position: Int): CharSequence? = mFragmentTitle[position]

    override fun getCount(): Int = mFragment.size

}