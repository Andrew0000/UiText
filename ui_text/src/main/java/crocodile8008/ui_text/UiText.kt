@file:Suppress("unused")

package crocodile8008.ui_text

import android.content.Context
import android.widget.TextView
import androidx.annotation.StringRes

sealed class UiText {

    data class Res(@StringRes val id: Int) : UiText()

    data class Str(val string: String) : UiText()

    data class Format(@StringRes val id: Int, val formatArgs: List<Any>) : UiText() {

        constructor(@StringRes id: Int, vararg formatVarArgs: Any): this(id, formatVarArgs.asList())
    }
}

fun UiText.getString(context: Context): String =
    when (this) {
        is UiText.Res -> context.getString(id)
        is UiText.Str -> string
        is UiText.Format -> context.getString(id, *formatArgs.toTypedArray())
    }

fun TextView.setUiText(uiText: UiText) {
    when (uiText) {
        is UiText.Res -> setText(uiText.id)
        is UiText.Str -> text = uiText.string
        is UiText.Format -> text = uiText.getString(context)
    }
}