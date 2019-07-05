@file:Suppress("DEPRECATION")

package id.aasumitro.made.data.source.pref

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


/**
 * Created by A. A. Sumitro on 07/02/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

object SharedPreferences {

    const val KEY_DAILY_REMINDER = "KEY_DAILY_REMINDER"
    const val KEY_RELEASE_REMINDER = "KEY_RELEASE_REMINDER"

    const val STATUS_ACTIVATE = "STATUS_ACTIVATE"
    const val STATUS_DEACTIVATE = "STATUS_DEACTIVATE"

    fun defaultPrefs(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

}
