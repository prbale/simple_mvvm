package com.example.mvvmkotlinexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmkotlinexample.base.Resource
import com.example.mvvmkotlinexample.base.Status
import com.example.mvvmkotlinexample.model.Message
import com.example.mvvmkotlinexample.repository.MainActivityRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivityViewModel(private val repository: MainActivityRepository) : ViewModel() {

    private val messageLiveData = MutableLiveData<Resource<Message>>()

    private val anythingElseLiveData = MutableLiveData<Resource<Unit>>()

    private val compositeDisposable = CompositeDisposable()

    init {
        fetchMessage()
    }

    private fun fetchMessage() {

        // Start Loading
        messageLiveData.postValue(Resource.loading(null))

        // Fetch Message
        compositeDisposable.add (
            repository.getMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        messageLiveData.postValue(Resource.success(it))
                    },
                    {
                        messageLiveData.postValue(Resource.error("An error has occurred", null))
                    }
                )
        )
    }

    fun getMessage(): MutableLiveData<Resource<Message>> {
        return messageLiveData
    }

    fun fetchAnythingElse() {

        // Start Loading
        messageLiveData.postValue(Resource.loading(null))

        // Fetch Anything else
        compositeDisposable.add (
            repository.getAnythingElse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        messageLiveData.postValue(Resource.success(null))
                    },
                    {
                        messageLiveData.postValue(Resource.error("An error has occurred", null))
                    }
                )
        )
    }

    fun getAnythingElse(): MutableLiveData<Resource<Unit>> {
        return anythingElseLiveData
    }

}