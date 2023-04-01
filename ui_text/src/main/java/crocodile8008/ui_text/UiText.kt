@file:Suppress("unused")

package crocodile8008.ui_text

import android.content.Context
import android.widget.TextView
import androidx.annotation.MainThread
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

    data class Custom(val factoryId: Int, val payload: Any?) : UiText()

    object CustomFactories {

        private val factories = mutableMapOf<Int, (Context, Any?) -> CharSequence>()

        @MainThread
        fun register(id: Int, factory: (Context, Any?) -> CharSequence) {
            factories[id] = factory
        }

        @MainThread
        fun unRegister(id: Int) {
            factories.remove(id)
        }

        @MainThread
        internal fun get(id: Int): ((Context, Any?) -> CharSequence)? =
            factories[id]
    }
}

fun UiText.getString(context: Context): CharSequence =
    when (this) {
        is UiText.Res -> context.getString(id)
        is UiText.Str -> string
        is UiText.Format -> context.getString(id, *formatArgs.toTypedArray())
        is UiText.Plural -> context.resources.getQuantityString(id, count)
        is UiText.PluralFormat -> context.resources.getQuantityString(id, count, *formatArgs.toTypedArray())
        is UiText.Custom -> {
            val factory = UiText.CustomFactories.get(factoryId)
            if (factory != null) {
                factory(context, payload)
            } else {
                ""
            }
        }
    }

@StringRes
fun UiText.getResId(): Int? =
    when(this) {
        is UiText.Res -> id
        is UiText.Plural -> id
        is UiText.Format -> id
        is UiText.PluralFormat -> id
        else -> null
    }

fun TextView.setUiText(uiText: UiText) {
    when (uiText) {
        is UiText.Res -> setText(uiText.id)
        is UiText.Str -> text = uiText.string
        else -> text = uiText.getString(context)
    }
}