package br.com.brunobrito.data.network.interceptors


import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class NetworkInterceptor  : Interceptor {
    private lateinit var response: Response
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request
        newRequest = request.newBuilder().build()
        response = chain.proceed(newRequest)
        return response.apply {
            if (this.code() != 200) {
                Log.e("okhttp_error", "\n\n---->STATUS CODE: " + this.code())
                Log.e("okhttp_error", "\n\n---->URL: " + this.request().url())
            }
        }
    }
}