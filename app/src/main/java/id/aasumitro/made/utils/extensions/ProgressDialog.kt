package id.aasumitro.made.utils.extensions

import android.content.Context
import androidx.core.content.ContextCompat
import com.kaopiz.kprogresshud.KProgressHUD
import id.aasumitro.made.R

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */


fun initProgressDialog(context: Context): KProgressHUD {
    return KProgressHUD.create(context)
        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
        .setCancellable(false)
        .setDimAmount(0.2f)
        .setCornerRadius(4f)
        .setSize(45, 45)
        .setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        .setAnimationSpeed(2)
}