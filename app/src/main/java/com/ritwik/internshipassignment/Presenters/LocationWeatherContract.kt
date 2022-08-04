package com.ritwik.internshipassignment.Presenters

import com.ritwik.internshipassignment.Data.WeatherData

interface LocationWeatherContract {

    interface WeatherListener
    {
        fun detailsFetched(weatherData: WeatherData)
    }
    interface LocationWeatherView
    {
        fun showDetails(weatherData: WeatherData)
    }
}