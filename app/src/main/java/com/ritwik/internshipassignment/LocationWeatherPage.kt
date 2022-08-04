package com.ritwik.internshipassignment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ritwik.internshipassignment.Data.LocationInfo
import com.ritwik.internshipassignment.Data.WeatherData
import com.ritwik.internshipassignment.Presenters.LocationWeatherContract
import com.ritwik.internshipassignment.databinding.ActivityLocationWeatherPageBinding
import com.squareup.picasso.Picasso

class LocationWeatherPage : AppCompatActivity(),LocationWeatherContract.LocationWeatherView {
    private lateinit var binding : ActivityLocationWeatherPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLocationWeatherPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val locationInfo = intent.extras!!.getParcelable<LocationInfo>("LOCATION_INFO")
        Log.d(TAG, "onCreate: title = ${locationInfo!!.locationTitle}")
        Log.d(TAG, "onCreate: LatLong = ${locationInfo.coOrdinates}")
        val weatherPresenter = WeatherPresenter(this,this)
        weatherPresenter.getData(locationInfo)
        
    }
    companion object
    {
        private const val TAG = "LocationWeatherPage"
    }

    override fun showDetails(weatherData: WeatherData) {

        binding.alwpTemperatureText.setText("${weatherData.temp}")
        binding.alwpPressure.setText("Pressure ${weatherData.pressure}")
        binding.alwpHumidity.setText("Humidity ${weatherData.humidity}")
        binding.alwpDesc.setText(weatherData.desc)
        if(weatherData.desc == "Clouds")
        {
            binding.alwpAnimation.setAnimation(R.raw.cloudy)
            binding.alwpAnimation.playAnimation()
            window.statusBarColor = Color.parseColor("#082952")
        }
        else if(weatherData.desc == "Drizzle"|| weatherData.desc == "Rain")
        {
            binding.alwpAnimation.setAnimation(R.raw.rainy)
            binding.alwpAnimation.playAnimation()
            window.statusBarColor = Color.parseColor("#365980")
        }
        else if(weatherData.desc == "Snow")
        {
            binding.alwpAnimation.setAnimation(R.raw.snowy)
            binding.alwpAnimation.playAnimation()
            window.statusBarColor = Color.parseColor(" #619DDA")

        }
        else if(weatherData.desc == "Windy")
        {
            binding.alwpAnimation.setAnimation(R.raw.windy)
            binding.alwpAnimation.playAnimation()
            window.statusBarColor = Color.parseColor(" #2C7F8A")
        }
        else
        {
            binding.alwpAnimation.setAnimation(R.raw.clear)
            binding.alwpAnimation.playAnimation()
            window.statusBarColor = Color.parseColor(" #00DBE9")
        }


    }

}