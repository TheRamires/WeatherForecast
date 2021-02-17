package com.mvcage.weatherforecast.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mvcage.weatherforecast.R
import com.mvcage.weatherforecast.databinding.ForecastFragmentBinding


class WeatherForecastFragment : Fragment() {
    private lateinit var binding: ForecastFragmentBinding

   private lateinit var tweetsRecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.forecast_fragment, container, false)

        binding.lifecycleOwner = this
        //recyclerView.layoutManager = LinearLayoutManager(this)
        return binding.root
    }

}