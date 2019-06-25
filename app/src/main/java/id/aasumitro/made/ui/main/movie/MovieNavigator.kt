package id.aasumitro.made.ui.main.movie

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

interface MovieNavigator  {

    fun onLoadData(status: Boolean)

    fun onErrorCallback(message: String)

}