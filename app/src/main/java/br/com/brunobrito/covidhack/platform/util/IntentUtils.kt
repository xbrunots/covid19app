package br.com.brunobrito.covidhack.platform.util

import android.widget.Toast
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import br.com.brunobrito.covidhack.R


class IntentUtils {
    companion object {
        fun openWebPage(activity: AppCompatActivity, url: String) {
            try {
                val webpage = Uri.parse(url)
                val myIntent = Intent(Intent.ACTION_VIEW, webpage)
                activity.startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    activity,
                    activity.getString(R.string.not_browser),
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }

        }
     }
}