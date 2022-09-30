package com.example.naverwebtooncloneproject

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class NetworkManager {

    companion object {
        @RequiresApi(Build.VERSION_CODES.M)
        fun checkNetworkState(context: Context): Boolean {
            val connectivityManager: ConnectivityManager =
                context.getSystemService(ConnectivityManager::class.java)
            val network: Network = connectivityManager.activeNetwork ?: return false
            val actNetwork: NetworkCapabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                else -> false
            }
        }
    }

}