package com.vaco.stackexchange.mvvm.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaco.stackexchange.mvvm.model.Item
import com.vaco.stackexchange.mvvm.model.Questions
import com.vaco.stackexchange.network.ApiInstance
import com.vaco.stackexchange.network.ApiService
import kotlinx.coroutines.launch

class ViewModels : ViewModel() {

    private var questions: MutableLiveData<MutableList<Questions>?> = MutableLiveData()

    fun getQuestionObservable(): MutableLiveData<MutableList<Questions>?> {
        return questions;
    }

    fun getQuestions(context: Context) {
        viewModelScope.launch {
            val apiInstance = ApiInstance.create().create(ApiService::class.java)
            try {
                val response = apiInstance.getQuestions("desc", "activity", "stackoverflow")
                val list = mutableListOf<Questions>()
                response.items.forEach {
                    if (it.is_answered && it.answer_count > 1) {
                        list.add(it)
                    }
                }
                questions.postValue(list)
            }
            catch (e: Exception) {
                e.message?.let { Log.d("ASESA", "ERROR: $it") }
                questions.postValue(null)
            }
        }
    }



}