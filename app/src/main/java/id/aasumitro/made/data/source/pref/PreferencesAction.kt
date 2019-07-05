package id.aasumitro.made.data.source.pref

import android.content.Context
import id.aasumitro.made.data.source.pref.SharedPreferences.get
import id.aasumitro.made.data.source.pref.SharedPreferences.set

/**
 * Created by A. A. Sumitro on 07/02/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class PreferencesAction(private val mContext: Context?) {

    /**
     * Shared Preferences Get Prefs Data
     * @param key
     */
    fun getPrefsData(key: String): String? {
        val mPrefs =
            SharedPreferences.defaultPrefs(mContext as Context)
        return mPrefs[key]
    }

    /**
     * Shared Preferences Set Prefs Data
     * @param key
     * @param value
     */
    fun setPrefsData(key: String, value: String) {
        val mPrefs =
            SharedPreferences.defaultPrefs(mContext as Context)
        mPrefs[key] = value
    }

}