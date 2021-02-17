package com.mvcage.weatherforecast.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.mvcage.weatherforecast.model.WeatherRealTime
import com.mvcage.weatherforecast.model.repository.DatabaseManager
import com.mvcage.weatherforecast.model.repository.Repository
import java.text.SimpleDateFormat
import java.util.*


class WeatherReceiver(): ViewModel(), LifecycleObserver   {

    private lateinit var repository: Repository

    var weatherRealTime : ObservableField<WeatherRealTime> = ObservableField()

    init {
        Log.i("ViewModel", "WeatherReceiver created!")
    }

    fun initWeatherReceiver(repository: Repository) {
        this.repository = repository
    }

    private lateinit var cityName : String

    fun getWeatherByCityName(s: CharSequence, start: Int, before: Int, count: Int) {
        cityName = "" + s.toString()
        if(cityName.length >= 6)
        {
            repository.getWeatherByCityName(cityName, weatherRealTime)
        }
        Log.w("tag", "onTextChanged $s")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun getWeatherInDefaultCity() {
        val defaultCity = "New York"
        repository.getWeatherByCityName(defaultCity, weatherRealTime)
        Log.w("tag", "getWeatherInDefaultCity")
    }
}