package com.example.marvelappapi.ui.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.marvelappapi.data.Marvel
import com.example.marvelappapi.data.MarvelRepository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private val dataRepo = MarvelRepository(app)
    val marvelData = dataRepo.marvelData

    val selectedMarvel = MutableLiveData<Marvel>()

    fun refeshData() {
        dataRepo.refreshData()
    }
}