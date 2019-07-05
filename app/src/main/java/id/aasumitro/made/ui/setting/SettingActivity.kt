@file:Suppress("DEPRECATION")

package id.aasumitro.made.ui.setting

import android.content.Intent
import android.preference.PreferenceManager
import android.provider.Settings.ACTION_LOCALE_SETTINGS
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id.aasumitro.made.R
import id.aasumitro.made.base.BaseActivity
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.data.source.pref.PreferencesAction
import id.aasumitro.made.data.source.pref.SharedPreferences.KEY_DAILY_REMINDER
import id.aasumitro.made.data.source.pref.SharedPreferences.KEY_RELEASE_REMINDER
import id.aasumitro.made.data.source.pref.SharedPreferences.STATUS_ACTIVATE
import id.aasumitro.made.data.source.pref.SharedPreferences.STATUS_DEACTIVATE
import id.aasumitro.made.services.DailyReceiver
import id.aasumitro.made.services.ReleaseTodayReceiver
import kotlinx.android.synthetic.main.content_setting.*
import me.ibrahimsn.library.LiveSharedPreferences
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class SettingActivity : BaseActivity(R.layout.activity_setting) {

    private var mPrefs: PreferencesAction? = null

    private val mViewModel: SettingViewModel by lazy {
        ViewModelProviders
            .of(this)
            .get(SettingViewModel::class.java)
    }

    override fun initView() {
        initToolbar(pageName = "DA'MOVIE - SETTING", back = true)
        mPrefs = PreferencesAction(this)

        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val liveSharedPreferences = LiveSharedPreferences(preferences)

        liveSharedPreferences.getString(
            KEY_RELEASE_REMINDER,
            STATUS_DEACTIVATE
        ).observe(this, Observer<String> { value ->
            when (value) {
                STATUS_ACTIVATE -> switch_release_reminder.isChecked = true
                STATUS_DEACTIVATE -> switch_release_reminder.isChecked = false
            }
        })

        liveSharedPreferences.getString(
            KEY_DAILY_REMINDER,
            STATUS_DEACTIVATE
        ).observe(this, Observer<String> { value ->
            when (value) {
                STATUS_ACTIVATE -> switch_daily_reminder.isChecked = true
                STATUS_DEACTIVATE -> switch_daily_reminder.isChecked = false
            }
        })

    }

    override fun initListener() {
        button_language.setOnClickListener {
            val mIntent = Intent(ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }

        switch_release_reminder.setOnCheckedChangeListener { _, isChecked ->
            val mReleaseToday = ReleaseTodayReceiver()
            when (isChecked) {
                true -> {
                    onSuccess(getString(R.string.text_activate))
                    mPrefs?.setPrefsData(
                        KEY_RELEASE_REMINDER,
                        STATUS_ACTIVATE
                    )

                    val date = Date()
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val currentDate = dateFormat.format(date)

                    mViewModel
                        .upcomingMovie()
                        .observe(this, Observer {
                            val mMovieList: ArrayList<Movie> = ArrayList()

                            it.forEach { movie ->
                                Log.d("ReleaseEquals", movie.releaseDate.equals(currentDate).toString())
                                if (movie.releaseDate.equals(currentDate)) {
                                    mMovieList.add(movie)
                                }
                            }

                            Log.d("Repeater", mMovieList.toString())
                            mReleaseToday.setRepeatingAlarm(this, mMovieList)
                        })
                }
                false -> {
                    onSuccess(getString(R.string.text_deactivate))
                    mPrefs?.setPrefsData(
                        KEY_RELEASE_REMINDER,
                        STATUS_DEACTIVATE
                    )
                    mReleaseToday.cancelAlarm(this)
                }
            }
        }

        switch_daily_reminder.setOnCheckedChangeListener { _, isChecked ->
            val mDailyReminder = DailyReceiver()
            when (isChecked) {
                true -> {
                    onSuccess(getString(R.string.text_activate))
                    mPrefs?.setPrefsData(
                        KEY_DAILY_REMINDER,
                        STATUS_ACTIVATE
                    )
                    mDailyReminder.setRepeatingAlarm(this)
                }
                false -> {
                    onSuccess(getString(R.string.text_deactivate))
                    mPrefs?.setPrefsData(
                        KEY_DAILY_REMINDER,
                        STATUS_DEACTIVATE
                    )
                    mDailyReminder.cancelAlarm(this)
                }
            }
        }
    }

}
