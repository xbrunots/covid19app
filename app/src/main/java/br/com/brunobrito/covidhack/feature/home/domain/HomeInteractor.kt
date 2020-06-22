package br.com.brunobrito.covidhack.feature.home.domain

import CasesCovid19Data
import CasesCovid19Resume
import CountryCases
import CountryCasesData
import br.com.brunobrito.covidhack.feature.home.data.HomeRepository
import io.reactivex.Observable

class HomeInteractor(private val repository: HomeRepository) : HomeContract.IInteractor {
    override fun getCasesInBrasil(): Observable<CasesCovid19Data> {
        return repository.getCasesInBrasil()
    }

    override fun getCasesInBrasilLastDay(casesNow: CasesCovid19Data): Observable<CasesCovid19Resume> {
        return repository.getCasesInBrasilLastDay(casesNow)
    }

    override fun getCasesInWorld(country: String): Observable<CountryCasesData> {
        return repository.getCasesInWorld(country)

    }
}