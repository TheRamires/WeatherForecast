package com.mvcage.weatherforecast.model.repository

import androidx.room.*
import com.mvcage.weatherforecast.model.WeatherRealTime


@Dao
interface IWeatherDAO {

    @Query("SELECT * FROM weatherrealtime")
    fun getAllWeatherToday(): List<WeatherRealTime>

    @Query("SELECT * FROM weatherrealtime WHERE name = :cityName")
    fun getWeatherTodayByCityName(cityName: String):WeatherRealTime

    @Query("SELECT * FROM weatherrealtime ORDER BY dt DESC")
    fun getWeatherTodayByLastDate() : WeatherRealTime

    @Insert
    fun insert(weatherrealtime: WeatherRealTime?)

    @Update
    fun update(weatherrealtime: WeatherRealTime?)

    @Delete
    fun delete(weatherrealtime: WeatherRealTime?)
}