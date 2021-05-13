package com.example.mvvmkotlinexample.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmkotlinexample.R
import com.example.mvvmkotlinexample.model.Message
import com.example.mvvmkotlinexample.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        btnClick.setOnClickListener {

            showLoading()

            mainActivityViewModel.getUser()?.observe(this, Observer { messageResponse ->

                hideLoading()

                displayMessage(messageResponse)
            })
        }
    }

    private fun displayMessage(messageResponse: Message) {
        lblResponse.text = messageResponse.message
    }

    private fun showLoading() = wp7progressBar.showProgressBar()

    private fun hideLoading() = wp7progressBar.hideProgressBar()
}
