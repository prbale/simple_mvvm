package com.example.mvvmkotlinexample.retrofit

import com.example.mvvmkotlinexample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val MAIN_URL = "https://run.mocky.io/v3/c0bc008f-9d6c-40d7-adb3-1449f83b71f4/"

    private val retrofitClient: Retrofit.Builder by lazy {

        // -------------  Logging -------------
        val logging = HttpLoggingInterceptor()
        logging.setLevel(
            if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
                Level.BODY else Level.NONE
        )
        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        // ------------- Retrofit -------------
        Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}
