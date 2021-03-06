package com.ariel.healthdelivery

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var locationMenager :LocationManager
    private lateinit var locationListener: LocationListener
    private var hasGps = false
    private  var locationGps: Location? = null

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1

    }


    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        getLocation()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        val localizacao = LatLng(locationGps!!.latitude, locationGps!!.longitude)
        mMap = googleMap
        mMap.setMyLocationEnabled(true);
        mMap.addMarker(MarkerOptions().position(localizacao).title("Location Order"))
        // Add a marker in Sydney and move the camera

        mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacao))



    }

    @SuppressLint("MissingPermission")
    private fun getLocation(){
        locationMenager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        hasGps = locationMenager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (hasGps){
            Log.d("CodeAndroidLocation", "hasGPS")
            locationMenager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,0F,object :LocationListener{
                override fun onLocationChanged(location: Location?) {
                    if (location != null){
                        locationGps = location
                    }
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                }

                override fun onProviderEnabled(provider: String?) {
                }

                override fun onProviderDisabled(provider: String?) {
                }

            })
            val localGpsLocation =  locationMenager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if(localGpsLocation != null)
                locationGps = localGpsLocation


        }

    }
}
