package crocodile8008.ui_text

import androidx.core.text.HtmlCompat

object AppCustomTextFactory {

    const val ID_HTML = 1

    fun register() {
        UiText.CustomFactories.register(
            id = ID_HTML,
            factory = { _, payload ->
                HtmlCompat.fromHtml(
                    "Custom factory: <font color=\"red\"><b>$payload</b></font>. OK",
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            }
        )
    }
}