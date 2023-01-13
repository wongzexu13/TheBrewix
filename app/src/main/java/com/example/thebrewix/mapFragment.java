package com.example.thebrewix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapFragment extends Fragment {

    GoogleMap mGoogleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //Display map with pinned location and custom title using Google Map API
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapView);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(new LatLng(2.832776015074785, 101.70742154454055));
                markerOptions.title("Xiamen University Malaysia DT");
//                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_circle_24));
                googleMap.clear();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(2.832776015074785, 101.70742154454055),10));
                googleMap.addMarker(markerOptions);

            }
        });
        return view;
    }

}