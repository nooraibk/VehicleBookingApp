package com.example.vehiclebookingapp.driver.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.vehiclebookingapp.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class DriverHomeFragment : Fragment(), OnMapReadyCallback {

    private val LOCATION_PERMISSION_REQUEST_CODE = 105
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener


    private lateinit var mMap : GoogleMap

/*    private val callback = OnMapReadyCallback { googleMap ->
        */
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this case, we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to
     * install it inside the SupportMapFragment. This method will only be triggered once the
     * user has installed Google Play services and returned to the app.
     *//*
        nMap = googleMap

        fetchLocation()

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }*/


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_user, container, false)

         val geocoder = Geocoder(requireContext(), Locale.getDefault())

        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = LocationListener { location ->
            val currentLatLng = LatLng(location.latitude, location.longitude)
            mMap.clear() // Remove previous markers
            mMap.addMarker(MarkerOptions().position(currentLatLng).title("Current Location"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))

            val addresses: List<Address> =
                geocoder.getFromLocation(location.latitude, location.longitude, 1) as List<Address>
            val cityName: String = addresses[0].locality
            val stateName: String = addresses[0].adminArea
            val countryName: String = addresses[0].countryName
            val address : String = addresses[0].subLocality

            Toast.makeText(requireContext(), "$address $cityName $stateName $countryName", Toast.LENGTH_LONG).show()
        }
       /* if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return rootView
        }else {
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
*/
        return rootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)


    }
    // Handle permission request result
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                startLocationUpdates()
            } else {
                // Permission denied
                Toast.makeText(requireContext(), "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Start location updates
    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        locationManager.removeUpdates(locationListener)
    }

    override fun onMapReady(map: GoogleMap) {
        mMap = map
    }
}
