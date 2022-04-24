package com.example.marvelappapi.data

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.example.marvelappapi.LOG_TAG
import com.example.marvelappapi.WEB_SERVICE_URL
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

//import com.example.androiddata.WEB_SERVICE_URL

class MarvelRepository(val app: Application) {

        val marvelData = MutableLiveData<List<Marvel>>()

    private val listType = Types.newParameterizedType(
            List::class.java, Marvel::class.java
    )

    init {
        refreshData()
    }

    @WorkerThread
    suspend fun callWebService() {
        if (networkAvailable()) {
            Log.i(LOG_TAG, "Calling Web Service")
            val retrofit = Retrofit.Builder()
                .baseUrl(WEB_SERVICE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            val service = retrofit.create(MarvelService::class.java)
            val serviceData = service.getMarvelData()
            marvelData.postValue((serviceData.data))
        }
    }

    @Suppress("DEPRECATION")
    private fun networkAvailable(): Boolean {
        val connectivityManager = app.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }

    fun refreshData() {
        CoroutineScope(Dispatchers.IO).launch {
            callWebService()
        }
    }
}