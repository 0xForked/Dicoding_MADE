package id.aasumitro.made.base

import android.content.IntentFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.kaopiz.kprogresshud.KProgressHUD
import id.aasumitro.made.R
import id.aasumitro.made.services.ConnectionReceiver
import id.aasumitro.made.utils.ConnectionInterface
import id.aasumitro.made.utils.NetworkInterface
import id.aasumitro.made.utils.extensions.initProgressDialog
import id.aasumitro.made.utils.extensions.showSnackBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.component_toolbar.*


/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

abstract class BaseActivity(
    @LayoutRes var layout: Int
) : AppCompatActivity(), ConnectionInterface,
    NetworkInterface {

    companion object {
        const val TAG = "BASE_ACTIVITY"
    }

    private var mConnectionReceiver: ConnectionReceiver? = null
    private var mProgressBar: KProgressHUD? = null

    private var isRegisteredReceiver: Boolean = false

    protected abstract fun initView()

    protected open fun initListener() {}

    fun initToolbar(
        back: Boolean = false,
        pageName: String = "",
        primary: Boolean = false
    ) {
        setSupportActionBar(toolbar)
        setPageName(pageName)
        supportActionBar?.let { actionBar ->
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(back)

            val backIcon = ContextCompat.getDrawable(
                this,
                R.drawable.ic_chevron_left_blue_24dp
            )
            backIcon?.setColorFilter(
                ContextCompat.getColor(
                    this,
                    R.color.colorTextAccent
                ), PorterDuff.Mode.SRC_ATOP
            )
            val iconColor = PorterDuffColorFilter(
                ContextCompat.getColor(
                    this,
                    if (primary)
                        R.color.colorTextAccent
                    else
                        R.color.colorTextPrimary
                ), PorterDuff.Mode.MULTIPLY
            )

            backIcon?.let {
                it.colorFilter = iconColor
                toolbar.overflowIcon?.colorFilter = iconColor
            }

            actionBar.setHomeAsUpIndicator(backIcon)
        }
    }

    fun setToolbarSearch(state: Boolean) {
        toolbar_search.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun setPageName(
        pageName: String
    ) {
        toolbar_title.text = pageName
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isRegisteredReceiver) {
            unregisterReceiver(mConnectionReceiver)
            isRegisteredReceiver = false
        }
    }

    override fun onPause() {
        super.onPause()
        if (isRegisteredReceiver) {
            unregisterReceiver(mConnectionReceiver)
            isRegisteredReceiver = false
        }
    }

    private fun initData(receiver: ConnectionInterface) {
        if (mConnectionReceiver == null) {
            mConnectionReceiver = ConnectionReceiver()
            mConnectionReceiver?.let {
                val mIntentFilter = IntentFilter()
                it.registerReceiver(receiver)
                mIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
                registerReceiver(mConnectionReceiver, mIntentFilter)
                isRegisteredReceiver = true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initData(this)
        super.onCreate(savedInstanceState)
        mProgressBar = initProgressDialog(this)
        setContentView(layout)
        initView()
        initListener()
    }

    override fun onProgress(isShow: Boolean) {
        mProgressBar?.let {
            if (isShow && !it.isShowing) {
                it.show()
            } else {
                it.dismiss()
            }
        }
    }

    override fun onConnectionChange(message: String) {
        Log.e(TAG, message)
        if (message != "Internet Connected") {
            container?.let {
                showSnackBar(this, it, message, Snackbar.LENGTH_LONG)
            }
        }
    }

    override fun onAlert(message: String, actionText: String, actionListener: Runnable) {
        onProgress(false)
        container?.let {
            showSnackBar(this, it, message, Snackbar.LENGTH_INDEFINITE, actionText, actionListener)
        }
    }

    override fun onError(message: String) {
        onProgress(false)
        container?.let {
            showSnackBar(this, it, message)
        }
    }

    override fun onSuccess(message: String) {
        onProgress(false)
        container?.let {
            showSnackBar(this, it, message, Snackbar.LENGTH_LONG)
        }
    }

    override fun onInfo(message: String) {
        onProgress(false)
        container?.let {
            showSnackBar(this, it, message)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item as MenuItem)
    }

}
