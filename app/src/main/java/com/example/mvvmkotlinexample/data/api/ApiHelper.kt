package com.example.mvvmkotlinexample.data.api

class ApiHelper(private val apiService: ApiService) {

    // Get Message
    fun getMessage() = apiService.getMessage()

    // Get anything else
    fun getAnythingElse() = apiService.getAnythingElse()

}