package id.aasumitro.made.utils

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */


interface NetworkInterface {

    fun onSuccess(message: String)

    fun onError(message: String)

    fun onInfo(message: String)

    fun onAlert(message: String, actionText: String, actionListener: Runnable)

    fun onProgress(isShow: Boolean = true)

}
