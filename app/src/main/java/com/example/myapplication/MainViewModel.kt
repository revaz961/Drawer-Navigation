package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> = _liveData

    private var isActive = false

    private lateinit var activeJob: Job

    fun getRequest() {
        isActive = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                while (isActive) {
                    request()
                    delay(5000)
                }
            }
        }
    }

    fun cancelRequest(){
        isActive = false
    }

    private suspend fun request() {
        _liveData.postValue("request")
    }
}