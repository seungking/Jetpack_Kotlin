package com.e.jetpack_kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    //db 생성, 설정 이름의 파일이 실제로 생성된다
    val db =
        Room.databaseBuilder(application, AppDatabase::class.java, "todo-db")
//                .allowMainThreadQueries() //그냥 빌드하면 백그라운드에서 작동하지 않으면 돌아가지 않지만
            // 이거 붙혀주면 메인 쓰레드에서 동작이 가능하다 / 실무에선 사용 x 연습할때 주로사용
            .build()

    var todos : LiveData<List<Todo?>>?

    init{
        todos = getAll()
    }

    var newTodo : String? = null

    fun getAll(): LiveData<List<Todo?>>? {
        return db.todoDao()?.getAll()
    }

    fun insert(todo:String){
        viewModelScope.launch(Dispatchers.IO) {
            db.todoDao()!!.insert(Todo(todo))
        }
    }

    fun delete(todo:String){
        return db.todoDao()!!.insert(db.todoDao()!!.get(todo))
    }

}