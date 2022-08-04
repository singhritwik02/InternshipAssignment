package com.ritwik.internshipassignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
import com.ritwik.internshipassignment.Data.LocationInfo
import com.ritwik.internshipassignment.Presenters.MainActivityContract
import com.ritwik.internshipassignment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(),MainActivityContract {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mapReadyClass = MapReadyClass()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(mapReadyClass)
        val navigationMenu = binding.amNavigationMenu
        val drawer = binding.amDrawer
        binding.amMenuButton.setOnClickListener {
            drawer.openDrawer(Gravity.LEFT)
        }
        navigationMenu.setNavigationItemSelectedListener {

            Toast.makeText(this, "${it.title}", Toast.LENGTH_SHORT).show()
            return@setNavigationItemSelectedListener true
        }

    }
    inner class MapReadyClass: OnMapReadyCallback
    {
        override fun onMapReady(p0: GoogleMap) {
            Log.d(TAG, "onMapReady: ")
            val map = p0
            // latitiude and longitude for indore
            val indoreLatLong = LatLng(22.7196,75.8577)
            // setting up marker options
            val markerOptions = MarkerOptions()
            markerOptions.title("Indore")
                .position(indoreLatLong)
            map.addMarker(markerOptions)
            map.moveCamera(CameraUpdateFactory.newLatLng(indoreLatLong))
            // setting on click listener for marker
            map.setOnMarkerClickListener {
                Toast.makeText(this@MainActivity, "${it.title}", Toast.LENGTH_SHORT).show()
                val locationInfo = it.title?.let { it1 -> LocationInfo(it1,it.position) }
                showLocationWeather(locationInfo)
                return@setOnMarkerClickListener false

            }


        }

    }
    companion object
    {
        private const val TAG = "MainActivity"
    }

    override fun showLocationWeather(locationInfo: LocationInfo?) {
        Log.d(TAG, "showLocationWeather: ")
        val intent = Intent(this,LocationWeatherPage::class.java)
        intent.putExtra("LOCATION_INFO",locationInfo)
        startActivity(intent)

    }
}