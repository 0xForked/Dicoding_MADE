package id.aasumitro.made.ui.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.RemoteViews
import android.widget.Toast
import id.aasumitro.made.R
import id.aasumitro.made.services.StackWidgetService


/**
 * Implementation of App Widget functionality.
 * @link source: https://developer.android.com/guide/topics/appwidgets
 */
class FavoriteWidget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // update each of the app widgets with the remote adapter
        appWidgetIds.forEach { appWidgetId ->

            // Sets up the intent that points to the StackViewService that will
            // provide the views for this collection.
            val intent = Intent(context, StackWidgetService::class.java).apply {
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                // When intents are compared, the extras are ignored, so we need to embed the extras
                // into the data so that the extras will not be ignored.
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))
            }
            val rv = RemoteViews(context.packageName, R.layout.widget_favorite).apply {
                setRemoteAdapter(R.id.stack_view, intent)

                // The empty view is displayed when the collection has no items. It should be a
                // sibling of the collection view.
                setEmptyView(R.id.stack_view, R.id.empty_view)
            }

            // This section makes it possible for items to have individualized behavior.
            // It does this by setting up a pending intent template. Individuals items of a
            // collection cannot set up their own pending intents. Instead, the collection as a
            // whole sets up a pending intent template, and the individual items set a fillInIntent
            // to create unique behavior on an item-by-item basis.
            val toastPendingIntent: PendingIntent = Intent(
                context,
                FavoriteWidget::class.java
            ).run {
                // Set the action for the intent.
                // When the user touches a particular view, it will have the effect of
                // broadcasting TOAST_ACTION.
                action = TOAST_ACTION
                putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
                data = Uri.parse(toUri(Intent.URI_INTENT_SCHEME))

                PendingIntent.getBroadcast(context, 0, this, PendingIntent.FLAG_UPDATE_CURRENT)
            }
            rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent)
            //rv.setOnClickPendingIntent(R.id.stack_view, toastPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, rv)
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.stack_view)
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds)
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == TOAST_ACTION) {
            val appWidgetId: Int = intent.getIntExtra(
                AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID
            )
            val viewIndex: Int = intent.getIntExtra(EXTRA_ITEM, 0)

            val appWidgetManager = AppWidgetManager.getInstance(context.applicationContext)
            val thisWidget = ComponentName(context, FavoriteWidget::class.java)
            val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.stack_view)

            Toast.makeText(
                context,
                "Touched view $viewIndex $appWidgetId $appWidgetIds",
                Toast.LENGTH_SHORT
            ).show()
        }
        super.onReceive(context, intent)
    }

    companion object {
        const val TOAST_ACTION = "id.aasumitro.made.TOAST_ACTION"
        const val EXTRA_ITEM = "id.aasumitro.made.EXTRA_ITEM"
        val TAG = FavoriteWidget::class.java.simpleName
    }

}
