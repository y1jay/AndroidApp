package com.test.nearbysearch;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.nearbysearch.place.Store;

import java.util.ArrayList;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<Store> storeArrayList = new ArrayList<>();

    double lat;
    double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        Intent i = getIntent();
        lat = i.getDoubleExtra("lat", 37.5412538);
        lng = i.getDoubleExtra("lng", 126.8359702);
        storeArrayList = (ArrayList<Store>) i.getSerializableExtra("all");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng me = new LatLng(lat,lng);

        for(Store store : storeArrayList){
            MarkerOptions options = new MarkerOptions()
                    .position(new LatLng(store.getLng(), store.getLat()))
                    .title(store.getName()).snippet(store.getVicinity());
            mMap.addMarker(options);
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(me, 16));
    }
}
