package com.mvcage.weatherforecast.model

import android.annotation.SuppressLint
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

@Entity
class WeatherRealTime {

    @PrimaryKey(autoGenerate = true)
    var idDb: Int = 0;
    @Embedded
    var coord: Coord? = null
    //@Embedded
    //var weather: List<Weather>? = null
    var base: String? = null
    @Embedded
    var main: Main? = null
    var visibility = 0
    @Embedded
    var wind: Wind? = null
    @Embedded
    var clouds: Clouds? = null
    var dt : Long = 0
    @Embedded
    var sys: Sys? = null
    var timezone = 0
    var name: String = ""
    var cod = 0


    @SuppressLint("SimpleDateFormat")
    fun gatDate():String
    {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        val netDate = Date(dt*1000)
        return sdf.format(netDate)
    }
}

class Coord {

    var lat: Float = 0f
    var lon : Float = 0f
}

class Weather {
    var id = 0
    var main: String? = null
    var description: String? = null
    var icon: String? = null
}

class Main {
    var temp:Float = 0.0f
    var feels_like = 0.0
    var temp_min = 0.0
    var temp_max = 0.0
    var pressure: Float = 0f
    var humidity = 0

    fun temperatureConverter():String {
        val celsius:Float = temp-273.15f
        return  celsius.roundToInt().toString() + " °C"
    }

    fun humidityCon():String{
        return "$humidity %"
    }

    fun pressureConverterMMHG():String{
        return ((pressure * 0.75f).roundToInt()).toString() + " mm Hg"
    }

}

class Wind {
    var speed : Float = 0f
    var deg = 0

    fun speedConverterMS():String
    {
        return "$speed m/s"
    }
}

class Clouds {
    var all = 0
}

class Sys {
    var type = 0
    var id = 0
    var country: String? = null
    var sunrise = 0
    var sunset = 0
}