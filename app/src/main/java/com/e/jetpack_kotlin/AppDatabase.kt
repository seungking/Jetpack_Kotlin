package com.e.jetpack_kotlin

import androidx.room.Database
import androidx.room.RoomDatabase

//데이터베이스 객체
@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    //객체가 접근하는 data access object (dao) 필요
    abstract fun todoDao(): TodoDao?
}