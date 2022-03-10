package com.vaco.stackexchange.mvvm.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaco.stackexchange.R
import com.vaco.stackexchange.adapters.QuestionsAdapter
import com.vaco.stackexchange.mvvm.viewmodel.ViewModels
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var viewModel: ViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initViewModel()
        initObserver()
    }

    private fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.hasFixedSize()
        questionsAdapter = QuestionsAdapter(applicationContext)
        recyclerView.adapter = questionsAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(ViewModels::class.java)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver() {
        viewModel.getQuestionObservable().observe(this) {
            if (it != null) {
                questionsAdapter.setQuestions(it)
                questionsAdapter.notifyDataSetChanged()
                questionsAdapter.setOnClickListener( object: QuestionsAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(it[position].link)
                        startActivity(intent)
                    }
                })
            }
        }
        viewModel.getQuestions(applicationContext)

    }

}