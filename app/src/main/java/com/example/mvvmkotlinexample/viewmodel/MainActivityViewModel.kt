package com.example.mvvmkotlinexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinexample.model.Message
import com.example.mvvmkotlinexample.repository.MainActivityRepository

class MainActivityViewModel : ViewModel() {

    var messageLiveData: MutableLiveData<Message>? = null

    fun getUser() : LiveData<Message>? {
        messageLiveData = MainActivityRepository.getMessageServiceCall()
        return messageLiveData
    }
}