package br.com.brunobrito.data.network.endpoints

class NetworkEndpoints {
    companion object {
        const val GET_BRASIL = "/api/report/v1"
        const val GET_MUNDO = "/api/report/v1/{country}"
        const val GET_INDATE = "/api/report/v1/Brazil/{date}"
    }
}