package ru.zinoviewk.dishesapp.core

import android.content.Context
import android.net.ConnectivityManager
import ru.zinoviewk.dishesapp.presentation.di.core.AppContext
import javax.inject.Inject

interface NetworkConnection {

    fun isAvailable(): Boolean
}

/**
 * Checks if there is internet connection
 * @param context is our ApplicationContext
 * */

class NetworkConnectionImpl @Inject constructor(
    @AppContext private val context: Context
) : NetworkConnection {

    override fun isAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}