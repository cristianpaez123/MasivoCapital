package com.example.masivocapital.sync

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkWatcher @Inject constructor(
    @ApplicationContext ctx: Context,
    private val enqueuer: SyncEnqueuer
) {
    private val cm = ctx.getSystemService(ConnectivityManager::class.java)

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            enqueuer.enqueueOnConnect()
        }
    }

    init {
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        cm.registerNetworkCallback(request, callback)
    }
}