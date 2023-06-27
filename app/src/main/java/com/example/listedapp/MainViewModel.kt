package com.example.listedapp

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.*
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val data = MutableLiveData<ResponseModel>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    fun getData(bearerToken: String) {
        Log.d("Thread Inside", Thread.currentThread().name)
        viewModelScope.launch {
            when (val response = mainRepository.getData(bearerToken)) {
                is NetworkState.Success -> {
                    data.postValue(response.data)
                }
                is NetworkState.Error -> {
                    if (response.response.code() == 401) {
                        print("error");
                    } else {
                        print("error1");
                    }
                }
            }
        }
    }


    private fun onError(message: String) {
        _errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


}