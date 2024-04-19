package com.example.storesearching;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.storesearching.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private String[][] data;

    public String[][] getData() {
        String[][] data ={
                {"Store UTAD", "41.28678538409359", "-7.740637471808735"},
                {"Store JLU", "43.82572225983296", "125.28501566529455"}};

        return data;
    }

    public static double stringToDouble(String str) {
        return Double.parseDouble(str);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String[][] store = getData();

        for(int i = 0; i < store.length; i++) {
            double lat = stringToDouble(store[i][1]);
            double lng = stringToDouble(store[i][2]);
            LatLng location = new LatLng(lat, lng);
            mMap.addMarker(new MarkerOptions().position(location).title(store[i][0]));
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}