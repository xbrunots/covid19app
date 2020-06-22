package br.com.brunobrito.covidhack.feature.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.brunobrito.covidhack.R
import br.com.brunobrito.covidhack.feature.home.presentation.HomeActivity
import br.com.brunobrito.covidhack.platform.extension.*
import kotlinx.android.synthetic.main.splash_activity.*


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        adjustTransucent()
        statusBarLightIcons()
        tvGitLineOne.fadeIn(1200) {
            tvGitLineTwo.fadeIn(500) {
                Handler().postDelayed({
                    HomeActivity.newInstance(this)
                }, 2400)
            }
        }
    }
}
