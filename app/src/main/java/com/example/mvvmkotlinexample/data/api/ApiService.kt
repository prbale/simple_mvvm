package com.example.mvvmkotlinexample.data.api

import com.example.mvvmkotlinexample.model.Message
import io.reactivex.Single

interface ApiService {

    fun getMessage(): Single<Message>

    fun getAnythingElse() : Single<Unit>
}