package com.mvcage.weatherforecast.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.mvcage.weatherforecast.R
import com.mvcage.weatherforecast.model.repository.DatabaseManager
import com.mvcage.weatherforecast.model.repository.Repository
import com.mvcage.weatherforecast.view.props.ZoomOutPageTransformer
import com.mvcage.weatherforecast.viewmodel.WeatherReceiver

private const val NUM_PAGES = 2

class MainActivity : FragmentActivity() {

    private lateinit var databaseManager: DatabaseManager
    private lateinit var repository : Repository
    private lateinit var weatherReceiver: WeatherReceiver
    private lateinit var mPager: ViewPager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        databaseManager = ViewModelProvider(this).get(DatabaseManager()::class.java)
        databaseManager.initDataBaseManager(this)
        repository = ViewModelProvider(this).get(Repository()::class.java)
        repository.initRepository(databaseManager)
        weatherReceiver = ViewModelProvider(this).get(WeatherReceiver()::class.java)
        weatherReceiver.initWeatherReceiver(repository)
        lifecycle.addObserver(weatherReceiver)
        setContentView(R.layout.activity_screen_slide)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager)
        mPager.setPageTransformer(true, ZoomOutPageTransformer())
        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
    }

    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "weather outside"
                1 -> return "weather forecast"
            }
            return "error"
        }

        override fun getItem(position: Int): Fragment{
            when (position) {
                0 -> return WeatherRealTimeFragment()
                1 -> return WeatherForecastFragment()
            }
            return WeatherRealTimeFragment()
        }
    }
}

