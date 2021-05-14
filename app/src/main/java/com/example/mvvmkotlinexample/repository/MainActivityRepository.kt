package com.example.mvvmkotlinexample.repository

import com.example.mvvmkotlinexample.data.api.ApiHelper
import com.example.mvvmkotlinexample.model.Message
import io.reactivex.Single

class MainActivityRepository(private val apiHelper: ApiHelper) {

    fun getMessage() : Single<Message> {
        return apiHelper.getMessage()
    }

    fun getAnythingElse(): Single<Unit> {
        return apiHelper.getAnythingElse()
    }

}