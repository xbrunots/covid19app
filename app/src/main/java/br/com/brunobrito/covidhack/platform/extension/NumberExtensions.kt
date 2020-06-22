package br.com.brunobrito.covidhack.platform.extension

import android.content.Context
import android.util.DisplayMetrics


fun Int.toDp(context: Context): Int {
    return this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT).toInt()
}

fun Float.toDp(context: Context): Float {
    return this / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}