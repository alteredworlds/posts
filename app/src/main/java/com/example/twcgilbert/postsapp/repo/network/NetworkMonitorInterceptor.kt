package com.example.twcgilbert.postsapp.repo.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.twcgilbert.postsapp.R
import okhttp3.Interceptor
import okhttp3.Response

class NetworkMonitorInterceptor(context: Application) : Interceptor {

    private val message: String
    private val connectivityManager: ConnectivityManager?

    init {
        message = context.getString(R.string.no_network_available)
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (isNetworkAvailable()) {
            return chain.proceed(chain.request())
        } else {
            throw NoNetworkException(message)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        return connectivityManager?.activeNetworkInfo?.isConnected ?: false
    }
}