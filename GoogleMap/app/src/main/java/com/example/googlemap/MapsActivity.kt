package com.example.googlemap

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationManager.GPS_PROVIDER
import android.location.LocationManager.NETWORK_PROVIDER
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.googlemap.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    lateinit var location: Location
    lateinit var locationListener: LocationListener
    lateinit var locationManager: LocationManager
    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button
    lateinit var edt_lat:EditText
    lateinit var bt_Mark:Button

    var lattitude = 0.00
    var longitude = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        edt_lat = findViewById(R.id.add_lat)
        bt_Mark= findViewById(R.id.btn_clik)

        bt_Mark.setOnClickListener {


            showMarkerOnMap()
        }

       /* binding.btnNxt.setOnClickListener {
            startActivity(Intent(applicationContext,AutoCompleteTextView::class.java))
        }*/

        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), 1)
        }


        var locationManager:LocationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        if(!locationManager.isProviderEnabled(GPS_PROVIDER))
        {
            Toast.makeText(applicationContext,"GPS is not working",Toast.LENGTH_LONG).show()
        }
        if(!locationManager.isProviderEnabled(NETWORK_PROVIDER))
        {
            Toast.makeText(applicationContext,"Internet is not working",Toast.LENGTH_LONG).show()
        }
        else
        {
            Toast.makeText(applicationContext,"Fetching Your Location",Toast.LENGTH_LONG).show()
        }

        var locationListener:LocationListener = object :LocationListener{

            override fun onLocationChanged(location: Location)
            {
                lattitude = location.getLatitude()
                longitude = location.getLongitude()

            }

        }
        if (locationManager.isProviderEnabled(NETWORK_PROVIDER))
        {
            // manager.requestLocationUpdates(NETWORK_PROVIDER, 0.0F, 0, locationListener)
            locationManager.requestLocationUpdates(NETWORK_PROVIDER,0,0.0F,locationListener)

            // locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,);
        }
        location = locationManager!!.getLastKnownLocation(NETWORK_PROVIDER)!!

        if(location!=null)
        {
            lattitude = location.getLatitude()
            longitude = location.getLongitude()
            Toast.makeText(applicationContext, ""+location.getLatitude(), Toast.LENGTH_SHORT).show()
        }



    if(location!=null)
        {
          /*  lattitude = location.getLatitude()
            longitude = location.getLongitude()*/

            lattitude = location.getLatitude()
            longitude = location.getLongitude()

            var stringRequest: StringRequest = object : StringRequest(
                Request.Method.POST,
                "https://compressible-approa.000webhostapp.com/Location_API/insert.php",
                Response.Listener {

                },
                {
                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_LONG)
                        .show()
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    var map = HashMap<String, String>()
                    map["lattitude"] = lattitude.toString()
                    map["longitude"] = longitude.toString()

                    return map
                }
            }
            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)
            Toast.makeText(applicationContext,"success",Toast.LENGTH_LONG).show()
        }
           Toast.makeText(applicationContext, "Latitude "  +  lattitude + "Longitude " + longitude , Toast.LENGTH_LONG).show()

        }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(lattitude, longitude)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))


        binding.btn1.setOnClickListener {

            googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
        binding.btn2.setOnClickListener {

            googleMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
        binding.btn3.setOnClickListener {

            googleMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }
        binding.btn4.setOnClickListener {

            googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        }
        binding.btn5.setOnClickListener {

            googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        }
    }


    private fun showMarkerOnMap()
    {
        val address = edt_lat.text.toString()

        if (address.isEmpty()) {
            Toast.makeText(this, "Please enter an address", Toast.LENGTH_SHORT).show()
            return
        }

        val geocoder = Geocoder(this)
        val addresses: List<Address> = geocoder.getFromLocationName(address, 1)!!

        if (addresses.isNotEmpty()) {
            val location = LatLng(addresses[0].latitude, addresses[0].longitude)
            mMap.clear() // Clear previous markers
            mMap.addMarker(MarkerOptions().position(location).title(address))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        } else {
            Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show()
        }
    }
}