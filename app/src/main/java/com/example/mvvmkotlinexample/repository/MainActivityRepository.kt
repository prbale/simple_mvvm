package com.example.mvvmkotlinexample.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mvvmkotlinexample.model.Message
import com.example.mvvmkotlinexample.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val messageResponseLiveData = MutableLiveData<Message>()

    fun getMessageServiceCall(): MutableLiveData<Message> {

        val call = RetrofitClient.apiInterface.getServices()

        call.enqueue(object: Callback<Message> {

            override fun onFailure(call: Call<Message>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(call: Call<Message>, response: Response<Message>) {
                val messageResponse: Message? = response.body()
                messageResponse?.let {
                    messageResponseLiveData.value = it
                }
            }
        })

        return messageResponseLiveData
    }
}