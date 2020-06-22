package br.com.brunobrito.covidhack.platform.device

import android.content.Context
import android.net.ConnectivityManager

class DeviceConnection(private var context: Context) {
    lateinit var deviceConnection: DeviceConnection

    fun newInstance(): DeviceConnection {
        if (!::deviceConnection.isInitialized) {
            deviceConnection = DeviceConnection(context)
        }
        return deviceConnection
    }

    fun isOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}