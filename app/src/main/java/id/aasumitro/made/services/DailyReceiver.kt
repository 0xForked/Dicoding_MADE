package id.aasumitro.made.services

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import id.aasumitro.made.R
import id.aasumitro.made.utils.extensions.showAlarmNotification
import java.util.*


/**
 * Created by A. A. Sumitro on 02/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class DailyReceiver : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = 445
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val mAppName = context?.getString(R.string.app_title)
        val mMessage = context?.getString(R.string.text_daily_reminder)
        showAlarmNotification(context, mAppName, mMessage, NOTIFICATION_ID)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun setRepeatingAlarm(context: Context) {
        cancelAlarm(context)
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis, getPendingIntent(context)
            )
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT &&
            Build.VERSION.SDK_INT < Build.VERSION_CODES.M
        ) {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                getPendingIntent(context)
            )
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                getPendingIntent(context)
            )
        }
    }

    fun cancelAlarm(context: Context) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(getPendingIntent(context))
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val alarmIntent = Intent(context, DailyReceiver::class.java)
        val isAlarmOn = PendingIntent.getBroadcast(
            context, NOTIFICATION_ID, alarmIntent,
            PendingIntent.FLAG_NO_CREATE
        ) != null

        Log.e("isAlarmOn : ", isAlarmOn.toString())

        return PendingIntent.getBroadcast(
            context,
            NOTIFICATION_ID,
            alarmIntent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
    }

}