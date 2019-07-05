package id.aasumitro.made.services

import android.content.Intent
import android.widget.RemoteViewsService
import id.aasumitro.made.ui.widget.StackRemoteViewsFactory


/**
 * Created by A. A. Sumitro on 03/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class StackWidgetService : RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return StackRemoteViewsFactory(this.applicationContext)
    }

}