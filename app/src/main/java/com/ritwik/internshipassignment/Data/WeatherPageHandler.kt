package com.ritwik.internshipassignment.Data

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ritwik.internshipassignment.Presenters.LocationWeatherContract
import org.json.JSONObject
import java.math.RoundingMode
import java.text.DecimalFormat

class WeatherPageHandler(val context:Context,val weatherListener: LocationWeatherContract.WeatherListener) {


    fun getData(locationInfo:LocationInfo)
    {
        Log.d(TAG, "getData: ")
        val apiKey = "dd5150a5db5e91f34d7cb75028075b80"
        val url = "https://api.openweathermap.org/data/2.5/weather?lat=${locationInfo.coOrdinates.latitude}&lon=${locationInfo.coOrdinates.longitude}&appid=$apiKey"
        Log.d(TAG, "getData: Url = $url")
        val requestQueue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(Request.Method.GET,url,null,object :Response.Listener<JSONObject>
        {
            override fun onResponse(response: JSONObject?) {
                response?.let {response->
                    Log.d(TAG, "onResponse: ")
                    val jsonObjectMain = response.getJSONObject("main")
                    val temperature = jsonObjectMain.getString("temp")
                    val tempDouble = temperature.toDouble() - 273.15
                    val df = DecimalFormat("#.##")
                    df.roundingMode = RoundingMode.DOWN
                    val temp = df.format(tempDouble)
                    val pressure = jsonObjectMain.getString("pressure")
                    val humidity = jsonObjectMain.getString("humidity")
                    val jsonObjectWeatherArray = response.getJSONArray("weather")
                    val mainDesc = jsonObjectWeatherArray.getJSONObject(0)
                        .getString("main")
                    val iconCode = jsonObjectWeatherArray.getJSONObject(0)
                        .getString("icon")
                    val iconLink = "https://openweathermap.org/img/w/$iconCode.png"
                    val weatherData = WeatherData()
                    weatherData.temp = temp.toDouble()
                    weatherData.iconLink = iconLink
                    weatherData.desc = mainDesc
                    weatherData.pressure = pressure
                    weatherData.humidity = humidity
                    weatherListener.detailsFetched(weatherData)
                }


            }

        },Response.ErrorListener {
            Log.d(TAG, "getData: ")
        })
        requestQueue.add(request)
    }

    companion object
    {
        private const val TAG = "WeatherPageHandler"
    }
}