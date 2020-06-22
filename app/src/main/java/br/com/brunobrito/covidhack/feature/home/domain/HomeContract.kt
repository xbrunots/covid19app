package br.com.brunobrito.covidhack.feature.home.domain

import CasesCovid19
import CasesCovid19Data
import CasesCovid19Resume
import CountryCases
import CountryCasesData
import br.com.brunobrito.covidhack.platform.base.BaseMvpPresenter
import br.com.brunobrito.covidhack.platform.base.BaseMvpView
import io.reactivex.Observable

interface HomeContract {
    interface IView : BaseMvpView {
        fun invokeView(nowData: List<CasesCovid19>, lastData: List<CasesCovid19>)
        fun invokeStories(nowData: List<CasesCovid19>, lastData: List<CasesCovid19>)
        fun invokeAdapter(list: List<CasesCovid19>)
        fun showApiError()
        fun showSearchView()
        fun hideSearchView()
        fun showConnectionError()
    }

    interface IPresenter : BaseMvpPresenter {
        fun filter(text: String)
        fun showSearch()
        fun hideSearch()
    }

    interface IInteractor {
        fun getCasesInBrasil(): Observable<CasesCovid19Data>
        fun getCasesInWorld(country: String): Observable<CountryCasesData>
        fun getCasesInBrasilLastDay(
            casesNow: CasesCovid19Data
        ): Observable<CasesCovid19Resume>
    }

    interface IRepository {
        fun getCasesInBrasil(): Observable<CasesCovid19Data>
        fun getCasesInWorld(country: String): Observable<CountryCasesData>
        fun getCasesInBrasilLastDay(
            casesNow: CasesCovid19Data
        ): Observable<CasesCovid19Resume>

    }

    interface IDataSource {
        fun getCasesInBrasil(): Observable<CasesCovid19Data>
        fun getCasesInWorld(country: String): Observable<CountryCasesData>
        fun getCasesInBrasilLastDay(
            casesNow: CasesCovid19Data
        ): Observable<CasesCovid19Resume>

    }
}