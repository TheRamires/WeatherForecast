package com.mvcage.weatherforecast.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mvcage.weatherforecast.R
import com.mvcage.weatherforecast.databinding.RealTimeFragmentBinding
import com.mvcage.weatherforecast.viewmodel.WeatherReceiver

class WeatherRealTimeFragment : Fragment()  {

    private lateinit var binding: RealTimeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.real_time_fragment, container, false)
        val weatherReceiver : WeatherReceiver by activityViewModels()
        binding.weatherReceiver =  weatherReceiver
        binding.lifecycleOwner = this

        return binding.root
    }
}