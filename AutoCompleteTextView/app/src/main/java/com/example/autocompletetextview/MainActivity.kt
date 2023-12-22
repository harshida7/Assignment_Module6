package com.example.autocompletetextview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import java.util.Arrays

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val apiKey = getString(R.string.api_key)
        // Initializing Places
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }
        // Initializing Autocomplete fragment
        val autocompleteSupportFragment = supportFragmentManager
            .findFragmentById(R.id.autoCompleteFragment) as AutocompleteSupportFragment?

        autocompleteSupportFragment!!.setPlaceFields(
            listOf(
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.PHONE_NUMBER,
                Place.Field.LAT_LNG,
                Place.Field.OPENING_HOURS,
                Place.Field.RATING,
                Place.Field.USER_RATINGS_TOTAL,
            )
        )

        autocompleteSupportFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: Status) {
                Toast.makeText(applicationContext, "ERROR $p0", Toast.LENGTH_SHORT).show()
            }

            @SuppressLint("SetTextI18n")
            override fun onPlaceSelected(p0: Place) {
                val textView = findViewById<TextView>(R.id.tv1)

                val name = p0.name
                val address = p0.address
                val phone = p0.phoneNumber
                val latLng = p0.latLng
                val latitude = latLng?.latitude
                val longitude = latLng?.longitude
                val isOpenStatus = if (p0.isOpen == true) {
                    "Open"
                } else {
                    "Closed"
                }
                val rating = p0.rating
                val userRatings = p0.userRatingsTotal

                textView.text = "Name: $name" +
                        "\nAddress: $address" +
                        "\nPhone Number: $phone" +
                        "\nLatitude: $latitude" +
                        "\nLongitude: $longitude" +
                        "\nIs Open: $isOpenStatus" +
                        "\nRating: $rating" +
                        "\nUser Ratings: $userRatings"
            }
        })
    }
}