package com.movies.android.ui.customs

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.movies.android.R
import com.movies.android.common.ui.extension.runIfNotNull

class CustomText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var fontType: FontType = FontType.FONT_REGULAR

    enum class FontType {
        FONT_REGULAR, FONT_BOLD
    }

    init {
        attrs.runIfNotNull {
            context.obtainStyledAttributes(attrs, R.styleable.CustomText, 0, 0).apply {
                fontType = FontType.values()[getInteger(
                    R.styleable.CustomText_fontType, FontType.FONT_REGULAR.ordinal
                )]
                typeface =
                    Typeface.createFromAsset(context.assets, "fonts/" + getFontName(fontType))
                recycle()
            }
        }
    }

    private fun getFontName(fontType: FontType): String {
        return when (fontType) {
            FontType.FONT_REGULAR -> "HelveticaNeue.otf"
            FontType.FONT_BOLD -> "HelveticaNeue_Bold.otf"
        }
    }
}