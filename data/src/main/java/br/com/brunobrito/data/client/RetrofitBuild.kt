package br.com.brunobrito.data.client

import br.com.brunobrito.data.BuildConfig
import br.com.brunobrito.data.network.interceptors.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuild {
    inline fun <reified T : Any> buildService(): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClientAndInterceptor())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create(T::class.java)
    }

    fun httpClientAndInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = provideLogLevel()
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(NetworkInterceptor())
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    private fun provideLogLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}