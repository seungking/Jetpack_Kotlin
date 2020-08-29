package com.e.jetpack_kotlin

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo?>>?
    //LiveData 설정

    @Insert
    fun insert(todo: Todo?)

    @Update
    fun update(todo: Todo?)

    @Delete
    fun delete(todo: Todo?)

    @Query("SELECT * FROM Todo WHERE title = :target")
    fun get(target : String): Todo?

}