package com.mvcage.weatherforecast.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mvcage.weatherforecast.model.WeatherRealTime

@Database(entities = [WeatherRealTime::class], version = 1,exportSchema = false)
abstract  class AppDatabase: RoomDatabase() {
    abstract fun weatherDao(): IWeatherDAO
}