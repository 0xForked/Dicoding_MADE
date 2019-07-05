package id.aasumitro.made.services

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import id.aasumitro.made.R
import id.aasumitro.made.data.entity.Movie
import id.aasumitro.made.utils.extensions.showAlarmNotification
import java.util.*

/**
 * Created by A. A. Sumitro on 02/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class ReleaseTodayReceiver : BroadcastReceiver() {

    companion object {
        var NOTIFICATION_ID = 446
        const val ID = "id"
        const val TITLE = "title"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val mNotifId = intent?.getIntExtra(ID, 0)
        val mTitle = intent?.getStringExtra(TITLE)
        val mMessage = context?.getString(R.string.text_release_info)
        showAlarmNotification(context, mTitle, mMessage, mNotifId as Int)
    }

    @SuppressLint("ObsoleteSdkInt")
    fun setRepeatingAlarm(context: Context, movies: List<Movie>) {
        var delay = 0
        for (movie in movies) {
            cancelAlarm(context)
            val alarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, ReleaseTodayReceiver::class.java)
            intent.putExtra(TITLE, movie.title)
            intent.putExtra(ID, NOTIFICATION_ID)

            val pendingIntent =
                PendingIntent.getBroadcast(context, 100, intent, FLAG_UPDATE_CURRENT)

            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, 8)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis + delay, pendingIntent
                )
            } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.M
            ) {
                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis + delay,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis + delay, pendingIntent
                )
            }

            NOTIFICATION_ID += 1
            delay += 5000
        }

    }

    fun cancelAlarm(context: Context) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(getPendingIntent(context))
    }

    private fun getPendingIntent(context: Context): PendingIntent {
        val alarmIntent = Intent(context, ReleaseTodayReceiver::class.java)
        val isAlarmOn = PendingIntent.getBroadcast(
            context, NOTIFICATION_ID, alarmIntent,
            PendingIntent.FLAG_NO_CREATE
        ) != null
        Log.e("idWhenCancel", NOTIFICATION_ID.toString() + isAlarmOn.toString())
        return PendingIntent.getBroadcast(
            context,
            NOTIFICATION_ID,
            alarmIntent,
            FLAG_UPDATE_CURRENT
        )
    }

}
