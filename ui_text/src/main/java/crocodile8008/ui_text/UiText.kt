@file:Suppress("unused")

package crocodile8008.ui_text

import android.content.Context
import android.widget.TextView
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class UiText {

    data class Res(@StringRes val id: Int) : UiText()

    data class Str(val string: String) : UiText()

    data class Plural(@PluralsRes val id: Int, val count: Int) : UiText()

    data class Format(@StringRes val id: Int, val formatArgs: List<Any>) : UiText() {

        constructor(@StringRes id: Int, vararg formatVarArgs: Any): this(id, formatVarArgs.asList())
    }

    data class PluralFormat(@PluralsRes val id: Int, val count: Int, val formatArgs: List<Any>) : UiText() {

        constructor(@PluralsRes id: Int, count: Int, vararg formatVarArgs: Any): this(id, count, formatVarArgs.asList())
    }
}

fun UiText.getString(context: Context): String =
    when (this) {
        is UiText.Res -> context.getString(id)
        is UiText.Str -> string
        is UiText.Format -> context.getString(id, *formatArgs.toTypedArray())
        is UiText.Plural -> context.resources.getQuantityString(id, count)
        is UiText.PluralFormat -> context.resources.getQuantityString(id, count, *formatArgs.toTypedArray())
    }

fun TextView.setUiText(uiText: UiText) {
    when (uiText) {
        is UiText.Res -> setText(uiText.id)
        is UiText.Str -> text = uiText.string
        is UiText.Format -> text = uiText.getString(context)
        is UiText.Plural -> text = uiText.getString(context)
        is UiText.PluralFormat -> text = uiText.getString(context)
    }
}