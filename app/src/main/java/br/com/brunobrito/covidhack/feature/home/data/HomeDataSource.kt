package br.com.brunobrito.covidhack.feature.home.data

import CasesCovid19Data
import CasesCovid19Resume
import CountryCasesData
import android.annotation.SuppressLint
import br.com.brunobrito.covidhack.feature.home.data.service.HomeService
import br.com.brunobrito.covidhack.feature.home.domain.HomeContract
import br.com.brunobrito.data.client.RetrofitBuild.buildService
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*


class HomeDataSource : HomeContract.IDataSource {
    override fun getCasesInBrasil(): Observable<CasesCovid19Data> {
        return buildService<HomeService>().getCasesInBrasil()
    }

    override fun getCasesInWorld(country: String): Observable<CountryCasesData> {
        return buildService<HomeService>().getCasesInWorld(country)
    }

    override fun getCasesInBrasilLastDay(
        casesNow: CasesCovid19Data
    ): Observable<CasesCovid19Resume> {
        var data = CasesCovid19Resume().apply {
            casesNow.casesMapper {
                this.lastCases = it.data
                this.nowCases = casesNow.data
            }
        }
        return Observable.just(data)
    }


    @SuppressLint("CheckResult")
    private fun CasesCovid19Data.casesMapper(
        result: (data: CasesCovid19Data) -> Unit
    ) {
        var dateFinal = ""
        if (this != null && this.data.isNotEmpty()) {
            var dataCrue = this.data[0].datetime //2020-06-19T14:33:25.000Z
            if (dataCrue != null) {

                val dateInString = dataCrue.split("T")[0]
                var sdf = SimpleDateFormat("yyyy-MM-dd")
                val c = Calendar.getInstance()
                c.time = sdf.parse(dateInString)
                c.add(Calendar.DATE, -1)
                sdf = SimpleDateFormat("yyyyMMdd")
                val resultdate = Date(c.timeInMillis)
                dateFinal = sdf.format(resultdate)

            } else {
                val formataData = SimpleDateFormat("yyyyMMdd")
                val cal = Calendar.getInstance()
                cal.add(Calendar.DAY_OF_YEAR, -1)
                dateFinal = formataData.format(cal.time)
            }
        }

        buildService<HomeService>().getCasesInDate(dateFinal).subscribe {
            result(it)
        }
    }
}

