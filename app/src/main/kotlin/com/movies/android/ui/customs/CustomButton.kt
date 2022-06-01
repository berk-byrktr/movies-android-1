package com.movies.android.ui.customs

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.movies.android.R
import com.movies.android.common.ui.extension.inflate
import com.movies.android.common.ui.extension.runIfNotNull
import kotlinx.android.synthetic.main.cv_button.view.*

class CustomButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var text: String = ""
        set(value) {
            field = value
            tv.text = text
        }

    var textColor: Int = R.color.textBlack
        set(value) {
            field = value
            tv.setTextColor(ContextCompat.getColor(context, textColor))
        }

    var fontType: Int = Typeface.NORMAL
        set(value) {
            field = value
            tv.setTypeface(tv.typeface, fontType)
        }

    init {
        inflate(R.layout.cv_button)
        attrs.runIfNotNull {
            getContext().obtainStyledAttributes(attrs, R.styleable.CustomButton).apply {
                getString(R.styleable.CustomButton_text)?.let { text = it }
                textColor = getResourceId(R.styleable.CustomButton_textColor, textColor)
                fontType = getInt(R.styleable.CustomButton_fontType, fontType)
                recycle()
            }
        }
    }
}