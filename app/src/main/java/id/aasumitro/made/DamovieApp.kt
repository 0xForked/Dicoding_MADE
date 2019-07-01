package id.aasumitro.made

import android.app.Application
import id.aasumitro.made.data.source.local.DamovieDb

/**
 * Created by A. A. Sumitro on 01/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class DamovieApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instanceDb()
    }

    fun instanceDb() = DamovieDb.getInstance(this)

}