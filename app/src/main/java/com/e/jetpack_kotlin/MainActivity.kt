package com.e.jetpack_kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //db 생성, 설정 이름의 파일이 실제로 생성된다
        val db =
            Room.databaseBuilder(this, AppDatabase::class.java, "todo-db")
                .allowMainThreadQueries() //그냥 빌드하면 백그라운드에서 작동하지 않으면 돌아가지 않지만
                // 이거 붙혀주면 메인 쓰레드에서 동작이 가능하다 / 실무에선 사용 x 연습할때 주로사용
                .build()

        result_text.text = db.todoDao()!!.getAll().toString();

        add_button.setOnClickListener {
            db.todoDao()!!.insert(Todo(input_todo.text.toString()))
            result_text.setText(db.todoDao()!!.getAll().toString())
        }

        //삭제 - title 같은 todo 찾아서 삭제
        delete_button.setOnClickListener(View.OnClickListener { v: View? ->
            db.todoDao()!!.delete(db.todoDao()!!.get(input_todo.getText().toString()))
            result_text.setText(db.todoDao()!!.getAll().toString())
        })
    }
}