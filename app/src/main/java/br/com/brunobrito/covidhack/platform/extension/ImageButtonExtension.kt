package br.com.brunobrito.covidhack.platform.extension

import android.graphics.PorterDuff
import android.widget.ImageButton

fun ImageButton.setTintColor(color: Int) {
    this.tag = color
    this.setColorFilter(resources.getColor(color), PorterDuff.Mode.SRC_ATOP)
}