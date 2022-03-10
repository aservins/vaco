package com.vaco.stackexchange.mvvm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.vaco.stackexchange.R
import com.vaco.stackexchange.mvvm.viewmodel.ViewModels

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initObserver()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ViewModels::class.java)
    }


    private fun initObserver() {

        viewModel.getQuestionObservable().observe(this) {
            if (it != null) {
                Log.d("ASESA", "SIZE: " + it.size)
            }
        }
        viewModel.getQuestions(applicationContext)

    }

}