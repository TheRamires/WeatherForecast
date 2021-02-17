package com.mvcage.weatherforecast.model.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.room.Room

class DatabaseManager(): ViewModel() {

    private lateinit  var context : Context
    private lateinit var database: AppDatabase

    init {
        Log.i("ViewModel", "DatabaseManager created!")
    }

    fun initDataBaseManager(context: Context)
    {
        this.context = context
        database = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    fun getDatabase(): AppDatabase {
        return database
    }
}