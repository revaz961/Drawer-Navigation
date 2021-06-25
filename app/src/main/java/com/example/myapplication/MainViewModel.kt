package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    private val _liveData = MutableLiveData<String>()
    val liveData:LiveData<String> = _liveData

    private var isActive = false

    fun getRequest(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                while(isActive){
                    request()
                    delay(5000)
                }
            }
        }
    }

    suspend fun request(){
        _liveData.postValue("request")
    }
}