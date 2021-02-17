package com.mvcage.weatherforecast.model.repository

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvcage.weatherforecast.model.Coord
import com.mvcage.weatherforecast.model.WeatherForecast
import com.mvcage.weatherforecast.model.WeatherRealTime
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(): ViewModel() {

    private lateinit var databaseManager: DatabaseManager
    private lateinit var db: AppDatabase
    private lateinit var weatherDao: IWeatherDAO

    fun initRepository(databaseManager: DatabaseManager) {
        this.databaseManager = databaseManager
        db  = databaseManager.getDatabase()
        weatherDao = db.weatherDao()
    }

    private var apiWeather : String = "https://api.openweathermap.org/data/2.5/"
    private  var apiKey : String ="f4ae474a5b174d30564edd613ca2e6a6"

    private val retrofit = Retrofit.Builder()
        .baseUrl(apiWeather)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherAPI: IWeatherAPI = retrofit.create(IWeatherAPI::class.java)

    fun getWeatherByCityName(cityName: String, bindResp: ObservableField<WeatherRealTime>) {
        val call: Call<WeatherRealTime> = weatherAPI.getWeatherTodayByCityName(cityName, apiKey)
        call.enqueue(object : Callback<WeatherRealTime> {
            override fun onResponse(call: Call<WeatherRealTime>, response: Response<WeatherRealTime>) {
                if(response.body()?.name!=null) {
                    Log.i("log", response.body()?.name + "- cityName")
                    bindResp.set(response.body()!!)
                    GlobalScope.launch  { weatherDao.insert(response.body()!!)}
                }
            }

            override fun onFailure(call: Call<WeatherRealTime>, t: Throwable?) {
                Log.i("response fail", t.toString())
                GlobalScope.launch  {
                    bindResp.set(weatherDao?.getWeatherTodayByLastDate()!!)
                }
            }
        })
    }

    fun getWeatherByCoordinates(lat: Float, lon: Float)
    {
        val call: Call<WeatherRealTime> = weatherAPI.getWeatherTodayByCoordinates(lat, lon, apiKey)
        val data = MutableLiveData<WeatherRealTime>()

        call.enqueue(object : Callback<WeatherRealTime> {
            override fun onResponse(
                call: Call<WeatherRealTime>,
                response: Response<WeatherRealTime>
            ) {
                data.value = response.body()
                getWeatherForecast(lat, lon)
            }

            override fun onFailure(call: Call<WeatherRealTime>, t: Throwable?) {
                Log.i("response ", "fail " + t?.cause.toString())
            }
        })
    }

    private fun getWeatherForecast(lat: Float, lon: Float)
    {
        val call: Call<WeatherForecast> = weatherAPI.getWeatherForecast(lat, lon, apiKey)
        val data = MutableLiveData<WeatherForecast>()
        Log.i("url", call.request().url().toString())

        call.enqueue(object : Callback<WeatherForecast> {
            override fun onResponse(
                call: Call<WeatherForecast>,
                response: Response<WeatherForecast>
            ) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                Log.i("response ", "fail " + t.cause.toString())
            }
        })
    }
}