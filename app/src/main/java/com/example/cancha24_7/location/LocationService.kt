package com.example.cancha24_7.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationService(context: Context) {

    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        10_000L
    ).setMinUpdateDistanceMeters(50f).build()

    private var callback: LocationCallback? = null

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(onLocation: (Double, Double) -> Unit) {

        callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val location = result.lastLocation ?: return
                onLocation(location.latitude, location.longitude)
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            callback!!,
            null
        )
    }

    fun stopLocationUpdates() {
        callback?.let {
            fusedLocationClient.removeLocationUpdates(it)
        }
        callback = null
    }
}