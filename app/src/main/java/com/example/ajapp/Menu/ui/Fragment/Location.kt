package com.example.ajapp.Menu.ui.Fragment

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.example.ajapp.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


@Suppress("DEPRECATION")
class Location : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_location, container, false)

        val mapFragment =
            requireActivity().fragmentManager.findFragmentById(R.id.mapNearBy) as MapFragment
        mapFragment?.getMapAsync(this)
        return view
    }



    //implemented methods of OnMapReadyCallback
    override fun onMapReady(googleMap: GoogleMap) {
//provided lat long
        val Madurai = LatLng(9.9252, 78.1198)
        googleMap.addMarker(
            MarkerOptions()
                .position(Madurai)
                .title("Destination 1")
        )
        //provided lat long
        val Dindigul = LatLng(10.3624, 77.9695)
        googleMap.addMarker(
            MarkerOptions()
                .position(Dindigul)
                .title("Destination 2")
        )


    }    }

