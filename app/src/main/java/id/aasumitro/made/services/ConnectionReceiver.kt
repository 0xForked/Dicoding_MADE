package id.aasumitro.made.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import id.aasumitro.made.utils.ConnectionInterface

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */


class ConnectionReceiver : BroadcastReceiver() {

    companion object {
        const val TAG = "CONNECTION_RECEIVER"
    }

    private var callback: ConnectionInterface? = null

    override fun onReceive(context: Context, arg1: Intent) {
        val connMgr =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        Log.e(TAG,"Internet Check")
        val builder = NetworkRequest.Builder()
        connMgr.registerNetworkCallback(builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network?) {
                    super.onAvailable(network)
                    showMessage("Internet Connected")
                }
                override fun onLost(network: Network?) {
                    super.onLost(network)
                    showMessage("No Internet Connection")
                }
            }
        )
    }

    private fun showMessage(message: String) {
        this.callback?.let {
            it.onConnectionChange(message)
            Log.e(TAG, message)
        } ?: run {
            Log.e(TAG,"No Callback for Internet State")
        }
    }

    fun registerReceiver(receiver: ConnectionInterface) {
        this.callback = receiver
    }

}