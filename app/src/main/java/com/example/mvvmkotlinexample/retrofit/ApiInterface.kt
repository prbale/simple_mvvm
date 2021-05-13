package com.example.mvvmkotlinexample.retrofit

import com.example.mvvmkotlinexample.model.Message
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("services")
    fun getServices() : Call<Message>

}