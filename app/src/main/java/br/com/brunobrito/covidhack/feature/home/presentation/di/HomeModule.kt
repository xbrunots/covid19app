package br.com.brunobrito.covidhack.feature.home.presentation.di

import br.com.brunobrito.covidhack.feature.home.data.HomeDataSource
import br.com.brunobrito.covidhack.feature.home.data.HomeRepository
import br.com.brunobrito.covidhack.feature.home.domain.HomeInteractor
import br.com.brunobrito.covidhack.feature.home.presentation.HomeActivity
import br.com.brunobrito.covidhack.feature.home.presentation.HomePresenter
import org.koin.dsl.module

object HomeModule {
    val module = module {
        single { HomeDataSource() }
        single { HomeRepository(get() as HomeDataSource) }
        single { HomeInteractor(get() as HomeRepository) }
           factory { (view: HomeActivity) ->
            HomePresenter(
                view
            )
        }
    }
}