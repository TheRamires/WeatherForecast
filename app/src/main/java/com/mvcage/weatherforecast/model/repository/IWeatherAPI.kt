package com.mvcage.weatherforecast.model.repository
import com.mvcage.weatherforecast.model.WeatherForecast
import com.mvcage.weatherforecast.model.WeatherRealTime
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherAPI {
    @GET("weather?")
    fun getWeatherTodayByCityName(@Query("q")cityName:String,
                                  @Query("appid") apiKey: String): Call<WeatherRealTime>

    //weather?lat={lat}&lon={lon}&appid={API key}

    @GET("weather?")
    fun getWeatherTodayByCoordinates(@Query("lat") lat: Float,
                                     @Query("lon") lon: Float,
                                     @Query("APIkey") apiKey:String): Call<WeatherRealTime>

    @GET("onecall?")
    fun getWeatherForecast(@Query("lat") lat:Float,
                           @Query("lon") lon : Float,
                           @Query("APIkey") apiKey:String): Call<WeatherForecast>
}