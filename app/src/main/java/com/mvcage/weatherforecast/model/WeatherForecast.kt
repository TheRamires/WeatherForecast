package com.mvcage.weatherforecast.model

class WeatherForecast {
    private var lat = 0f
    private var lon = 0f
    private var timezone: String? = null
    private var timezone_offset = 0f

    var minutely = ArrayList<Any>()
    var hourly = ArrayList<Any>()
    var daily = ArrayList<DailyForecast>()

    fun getLat(): Float {
        return lat
    }

    fun getLon(): Float {
        return lon
    }

    fun getTimezone(): String? {
        return timezone
    }

    fun getTimezone_offset(): Float {
        return timezone_offset
    }

    fun setLat(lat: Float) {
        this.lat = lat
    }

    fun setLon(lon: Float) {
        this.lon = lon
    }

    fun setTimezone(timezone: String?) {
        this.timezone = timezone
    }

    fun setTimezone_offset(timezone_offset: Float) {
        this.timezone_offset = timezone_offset
    }
}
class DailyForecast {
    var dt : Int = 0
    var sunrise : Int  = 0
    var sunset : Int  = 0
    var temp: Temperature? = null
    var feels_like: Feels_like? = null
    var pressure : Int  = 0
    var humidity = 0f
    var dew_point = 0f
    var wind_speed = 0f
    var wind_deg = 0f
    var weather = ArrayList<Weather>()
    var clouds = 0f
    var pop = 0f
    var rain = 0f
    var uvi = 0f

}

class Feels_like {
    // Setter Methods
    // Getter Methods
    var day = 0f
    var night = 0f
    var eve = 0f
    var morn = 0f

}

class Temperature {
    // Setter Methods
    // Getter Methods
    var day = 0f
    var min = 0f
    var max = 0f
    var night = 0f
    var eve = 0f
    var morn = 0f
}


