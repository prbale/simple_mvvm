package com.example.mvvmkotlinexample.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.base.Status
import com.example.mvvmkotlinexample.base.ViewModelFactory
import com.example.mvvmkotlinexample.data.api.ApiHelper
import com.example.mvvmkotlinexample.data.api.ApiServiceImpl
import com.example.mvvmkotlinexample.model.Message
import com.example.mvvmkotlinexample.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()
        setViewModel()

    }

    private fun setViewModel() {
        mainActivityViewModel = ViewModelProvider(
            this,
            ViewModelFactory(ApiHelper(ApiServiceImpl()))
        ).get(MainActivityViewModel::class.java)
    }

    private fun setupUI() {
        btnClick?.setOnClickListener {

            mainActivityViewModel.getMessage()
                .observe(this, Observer {
                when(it.status) {
                    Status.LOADING -> {
                        showLoading()
                    }
                    Status.SUCCESS -> {
                        hideLoading()
                        displayMessage(it.data)
                    }
                    Status.ERROR -> {
                        hideLoading()
                        displayError(it.message)
                    }
                }
            })
        }
    }

    private fun displayError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun displayMessage(messageResponse: Message?) {
        lblResponse.text = messageResponse?.message ?: ""
    }

    private fun showLoading() = wp7progressBar.showProgressBar()

    private fun hideLoading() = wp7progressBar.hideProgressBar()
}
