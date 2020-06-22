package br.com.brunobrito.covidhack.feature.home.data

import CasesCovid19Data
import CasesCovid19Resume
import CountryCases
import CountryCasesData
import br.com.brunobrito.covidhack.feature.home.domain.HomeContract
import io.reactivex.Observable

class HomeRepository(private val dataSource: HomeDataSource) : HomeContract.IRepository {
    override fun getCasesInBrasil(): Observable<CasesCovid19Data> {
        return dataSource.getCasesInBrasil()
    }

    override fun getCasesInBrasilLastDay(
        casesNow: CasesCovid19Data
    ): Observable<CasesCovid19Resume> {
        return dataSource.getCasesInBrasilLastDay( casesNow)
    }

    override fun getCasesInWorld(country: String): Observable<CountryCasesData> {
        return dataSource.getCasesInWorld(country)

    }

}