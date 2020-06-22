package br.com.brunobrito.covidhack.feature.home.presentation

import CasesCovid19
import android.R.attr
import br.com.brunobrito.covidhack.feature.home.domain.HomeContract
import br.com.brunobrito.covidhack.feature.home.domain.HomeInteractor
import br.com.brunobrito.covidhack.platform.device.DeviceConnection
import br.com.brunobrito.data.source.local.cache
import br.com.brunobrito.data.source.local.findCacheByModel
import br.com.brunobrito.data.source.local.findCacheByName
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.text.SimpleDateFormat
import java.util.*


class HomePresenter(val view: HomeContract.IView) : HomeContract.IPresenter, KoinComponent {

    private val ioScheduler: Scheduler = Schedulers.io()
    private val mainScheduler: Scheduler = AndroidSchedulers.mainThread()
    private val interactor: HomeInteractor by inject()
    private val deviceConnection: DeviceConnection by inject()
    private val compositeDisposable = CompositeDisposable()

    companion object {
        const val CACHE_CASES_NOW = "CACHE_CASES_NOW"
        const val CACHE_CASES_LAST = "CACHE_CASES_LAST"
    }

    override fun attachView(pais: String) {
        //20200615
        if (deviceConnection.isOnline()) {
            compositeDisposable.add(interactor.getCasesInBrasil()
                .flatMap {
                    interactor.getCasesInBrasilLastDay(it)
                }
                .observeOn(mainScheduler)
                .subscribeOn(ioScheduler)
                .doOnSubscribe { view.showLoading() }
                .doFinally { view.hideLoading() }
                .subscribe({
                    view.invokeView(it.nowCases, it.lastCases)
                    cache(CACHE_CASES_NOW, it.nowCases)
                    cache(CACHE_CASES_LAST, it.lastCases)
                    view.invokeStories(it.nowCases, it.lastCases)
                }, {
                    view.showApiError()
                })
            )

        } else {
            view.invokeView(
                findCacheByName(CACHE_CASES_NOW) ?: listOf(),
                findCacheByName(CACHE_CASES_LAST) ?: listOf()
            )
            view.showConnectionError()
        }
    }

    override fun filter(text: String) {
        findCacheByName<List<CasesCovid19>>(CACHE_CASES_NOW).let {
            val newList = it?.let { issuesIt ->
                issuesIt.filter { issuesItFiltered ->
                    issuesItFiltered.state?.let { it.contains(text, true) } ?: false
                }
            }
            view.invokeAdapter(newList ?: listOf())
        }
    }

    override fun showSearch() {
        view.showSearchView()
    }

    override fun hideSearch() {
        view.hideSearchView()
    }

    override fun detachView() {
        compositeDisposable.dispose()
    }
}