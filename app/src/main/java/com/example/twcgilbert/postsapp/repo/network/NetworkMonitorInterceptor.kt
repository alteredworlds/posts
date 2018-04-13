package com.example.twcgilbert.postsapp.repo.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import com.example.twcgilbert.postsapp.R
import okhttp3.Interceptor
import okhttp3.Response

class NetworkMonitorInterceptor(private val context: Application) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (isNetworkAvailable()) {
            return chain.proceed(chain.request())
        } else {
            throw NoNetworkException(context.getString(R.string.no_network_available))
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return cm?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}