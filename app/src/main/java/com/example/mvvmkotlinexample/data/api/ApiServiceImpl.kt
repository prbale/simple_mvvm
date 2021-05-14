package com.example.mvvmkotlinexample.data.api

import android.os.Handler
import com.example.mvvmkotlinexample.model.Message
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class ApiServiceImpl : ApiService {

    override fun getMessage(): Single<Message> {
        return Single.just(Message("Hello Prashant !!"))

    }

    override fun getAnythingElse(): Single<Unit> {
        return Single.just(Unit)
    }
}