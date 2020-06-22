package br.com.brunobrito.covidhack

import android.app.Application
import android.content.Context
import br.com.brunobrito.covidhack.platform.di.KogitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KogitApplication : Application() {
    companion object {
        lateinit var mApplication: Application

        private fun getApplication(): Application {
            return mApplication
        }

        fun getContext(): Context {
            return getApplication().applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mApplication = this
        startKoin {
            androidContext(mApplication)
            modules(
                KogitModule.getModules()
            )
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }
}