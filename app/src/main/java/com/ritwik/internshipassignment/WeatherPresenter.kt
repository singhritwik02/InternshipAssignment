package com.ritwik.internshipassignment

import android.content.Context
import android.util.Log
import com.ritwik.internshipassignment.Data.LocationInfo
import com.ritwik.internshipassignment.Data.WeatherData
import com.ritwik.internshipassignment.Data.WeatherPageHandler
import com.ritwik.internshipassignment.Presenters.LocationWeatherContract

class WeatherPresenter(private val weatherView:LocationWeatherContract.LocationWeatherView,val context: Context):LocationWeatherContract.WeatherListener {
    private val weatherPageHandler = WeatherPageHandler(context,this)

    fun getData(locationInfo: LocationInfo)
    {
        Log.d(TAG, "getData: ")
        weatherPageHandler.getData(locationInfo)
    }
    override fun detailsFetched(weatherData: WeatherData) {
        weatherView.showDetails(weatherData)
    }
    companion object
    {
        private const val TAG = "WeatherPresenter"
    }
}