package br.com.brunobrito.covidhack.feature.home.data.service

import CasesCovid19Data
import CountryCasesData
import br.com.brunobrito.data.network.endpoints.NetworkEndpoints.Companion.GET_BRASIL
import br.com.brunobrito.data.network.endpoints.NetworkEndpoints.Companion.GET_INDATE
import br.com.brunobrito.data.network.endpoints.NetworkEndpoints.Companion.GET_MUNDO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeService {
    @GET(GET_BRASIL)
    fun getCasesInBrasil(): Observable<CasesCovid19Data>

    @GET(GET_MUNDO)
    fun getCasesInWorld(@Path("country") country: String = "Brazil"): Observable<CountryCasesData>

    @GET(GET_INDATE)
    fun getCasesInDate(@Path("date") date: String): Observable<CasesCovid19Data>
}