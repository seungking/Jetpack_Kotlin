package com.e.jetpack_kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel =
            ViewModelProvider(this, AndroidViewModelFactory(application)).get(
                MainViewModel::class.java
            )

        //UI 갱신
        //livedata observe
        viewModel.getAll()!!.observe(this, Observer {
            result_text.text = it.toString();

        })

        add_button.setOnClickListener {
            //비동기 작업
            //Dispathchers.IO -> 백그라운드에서 동작
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.insert(Todo(input_todo.text.toString()))
            }
//            result_text.setText(db.todoDao()!!.getAll().toString())
        }

        //삭제 - title 같은 todo 찾아서 삭제
        delete_button.setOnClickListener(View.OnClickListener { v: View? ->

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.delete(input_todo.getText().toString())
//            result_text.setText(db.todoDao()!!.getAll().toString())
            }
        })
    }

    //코틀린은 coroutine이 있고 자바에서는 제공 안하는 쓰래딩 방법

}