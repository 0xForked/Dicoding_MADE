package id.aasumitro.made.utils.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import id.aasumitro.made.R
import id.aasumitro.made.utils.Constants


/**
 * Created by A. A. Sumitro on 02/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */

class CustomEditText : AppCompatEditText {

    companion object {
        const val TAG = "CUSTOM_EDIT_TEXT"
    }

    private var mEditTextType: Int = 0
    private var mFontStyle: Int = 0
    private var mFontName: Int = 0

    constructor(context: Context) : super(context)

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        setValues(attrs)
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        background = ContextCompat.getDrawable(
            context,
            Constants.View.Input[mEditTextType]
        )
        setFont(mFontStyle, mFontName)
        setNewTypeFace()
    }

    private fun setNewTypeFace() {
        val font = Typeface.createFromAsset(
            context.assets,
            Constants.View.Name[mFontName] + Constants.View.Style[mFontStyle] + Constants.View.Type
        )
        setTypeface(font, Typeface.NORMAL)
    }

    @SuppressLint("CustomViewStyleable")
    private fun setValues(attrs: AttributeSet?) {
        val attr = context.obtainStyledAttributes(attrs, R.styleable.BaseView)
        try {
            val n = attr.indexCount
            for (i in 0 until n) {
                when (val attribute = attr.getIndex(i)) {
                    R.styleable.BaseView_base_edit_text_style -> mEditTextType = attr.getInt(attribute, 0)
                    R.styleable.BaseView_base_font_style -> mFontStyle = attr.getInt(attribute, 0)
                    R.styleable.BaseView_base_font_family -> mFontName = attr.getInt(attribute, 0)
                    else -> Log.i(TAG, "$javaClass: $attribute")
                }
            }
        } finally {
            attr.recycle()
        }
    }

    private fun setFont(font: Int, name: Int) {
        mFontStyle = font
        mFontName = name
    }

}