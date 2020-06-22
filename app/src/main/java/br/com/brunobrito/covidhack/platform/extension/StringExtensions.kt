package br.com.brunobrito.covidhack.platform.extension

import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.Spanned


fun String.toShortDate(): String {
    return if (this == null) {
        ""
    } else {
        this.split("-")[2].split("T")[0] + "/" +
                this.split("-")[1] + "/" +
                this.split("-")[0]
    }
}

fun String.toLongDate(): String {
    return if (this == null) {
        ""
    } else {
        this.split("-")[2].split("T")[0] + "/" +
                this.split("-")[1] + "/" +
                this.split("-")[0]
    }
}

fun String.convertToHTML(): Spanned {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(this)
    }
}

fun String.toIntColor(): Int {
    var color = this
    if (!color.contains("#")) color = "#$this"

    return Color.parseColor(color)
}

fun String.openIsBoolen(): Boolean {
    return this.trim().toLowerCase() == "open"
}

fun String.toStatePT(): String {
    return if (this.trim().toLowerCase() == "open") "Aberto" else "Fechado"
}