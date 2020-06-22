package br.com.brunobrito.covidhack.platform.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.StrictMode
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.platform.logger.AndroidLogger
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.inject


@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {
    val logger: AndroidLogger by inject()

    override fun onStart() {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onStart()
    }

    fun onError(v: View, error: String) {
        Snackbar.make(v, error, Snackbar.LENGTH_LONG).show()
    }

    fun onInternetError(v: View, error: String, response: () -> Unit) {
        val snack = Snackbar.make(v, error, Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { response() }
        snack.setActionTextColor(Color.YELLOW)
        snack.show()
    }
}