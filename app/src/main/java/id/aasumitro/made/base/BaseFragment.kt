package id.aasumitro.made.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import id.aasumitro.made.utils.extensions.showSnackBar
import id.aasumitro.made.utils.NetworkInterface
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by A. A. Sumitro on 24/06/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

abstract class BaseFragment(
    @LayoutRes var layout: Int
) : Fragment(), NetworkInterface {

    private var mBaseActivity: BaseActivity? = null

    protected abstract fun initView()

    protected open fun initListener() {}

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        if(activity is BaseActivity) {
            mBaseActivity = activity as BaseActivity
        }

        initView()
        initListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            layout,
            container,
            false
        )
    }

    override fun onAlert(
        message: String,
        actionText: String,
        actionListener: Runnable
    ) {
        mBaseActivity?.let { activity ->
            container?.let {
                showSnackBar(
                    activity,
                    it,
                    message,
                    Snackbar.LENGTH_INDEFINITE,
                    actionText,
                    actionListener
                )
            }
        }
    }

    override fun onError(message: String) {
        mBaseActivity?.let { activity ->
            container?.let {
                showSnackBar(
                    activity,
                    it,
                    message
                )
            }
        }
    }

    override fun onSuccess(message: String) {
        mBaseActivity?.let { activity ->
            container?.let {
                showSnackBar(
                    activity,
                    it,
                    message,
                    Snackbar.LENGTH_LONG
                )
            }
        }
    }

    override fun onInfo(message: String) {
        mBaseActivity?.let { activity ->
            container?.let {
                showSnackBar(
                    activity,
                    it,
                    message
                )
            }
        }
    }

    override fun onProgress(isShow: Boolean) {
        mBaseActivity?.onProgress(isShow)
    }

}
