package com.mvcage.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import com.mvcage.weatherforecast.R
import com.mvcage.weatherforecast.databinding.HeaderFragmentBinding
import com.mvcage.weatherforecast.model.WeatherRealTime
import com.mvcage.weatherforecast.viewmodel.WeatherReceiver

class HeaderFragment : Fragment() {

    private var weatherRealTimeLive = MutableLiveData<WeatherRealTime>()

    private lateinit var headerDataBinding: HeaderFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        headerDataBinding = DataBindingUtil.inflate(inflater,
            R.layout.header_fragment, container, false)
        val weatherReceiver : WeatherReceiver by activityViewModels()
        headerDataBinding.weatherReceiver = weatherReceiver
        headerDataBinding.lifecycleOwner = this

        return headerDataBinding.root
    }
}
