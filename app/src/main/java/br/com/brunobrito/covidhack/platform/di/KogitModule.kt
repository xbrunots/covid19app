package br.com.brunobrito.covidhack.platform.di

import br.com.brunobrito.covidhack.feature.home.presentation.di.HomeModule
import br.com.brunobrito.covidhack.platform.device.DeviceConnection
import br.com.brunobrito.covidhack.platform.logger.AndroidLogger
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

object KogitModule {
    fun getModules(): List<Module> {
        return listOf(
            internet,
            logger,
            HomeModule.module
        )
    }

    private val internet = module {
        single { DeviceConnection(androidContext()).newInstance() }
    }

    private val logger = module {
        single { AndroidLogger(androidContext()) }
    }
}