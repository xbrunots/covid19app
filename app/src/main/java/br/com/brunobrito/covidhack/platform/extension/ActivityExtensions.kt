package br.com.brunobrito.covidhack.platform.extension

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.brunobrito.covidhack.platform.device.DeviceProperties


fun AppCompatActivity.statusBarDarkIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

fun AppCompatActivity.statusBarLightIcons() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        window.decorView.systemUiVisibility = 0
    }
}

fun AppCompatActivity.adjustTransucent() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        val w = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
    val viewGroup =
        (this.findViewById(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup
    viewGroup.setPadding(
        0,
        DeviceProperties.getStatusBarHeight(),
        0,
        DeviceProperties.getNavbarHeight()
    )
}

fun AppCompatActivity.setStatusThemeColor(color: Int, isDarkIcons: Boolean = false) {
    try {
        val window = this.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, color)
            window.navigationBarColor =  ContextCompat.getColor(this, color)
        }
        if (isDarkIcons) this.statusBarDarkIcons() else this.statusBarLightIcons()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatActivity.setStatusBarColor(color: Int) {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = this.window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            val decorView: View = window.decorView
            val view = View(this)
            val statusBarHeight: Int = DeviceProperties.getStatusBarHeight()
            view.layoutParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight
            )
            view.setBackgroundColor(ContextCompat.getColor(this, color))
            (decorView as ViewGroup).addView(view)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

