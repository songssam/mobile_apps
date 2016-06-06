package edu.uco.ssong3.p7Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap map;

    private LatLng newCity;
    public String name, temp;
    private Double lat, lon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent i = getIntent();
        temp = i.getStringExtra("temp");
        name = i.getStringExtra("name");
        lon = Double.parseDouble(i.getStringExtra("lon"));
        lat = Double.parseDouble(i.getStringExtra("lat"));
        newCity = new LatLng(lat, lon);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        this.map = map;
        // move the camera to CS building
        CameraPosition camera = new CameraPosition.Builder()
                .target(newCity).zoom(12).build();
        map.clear(); // clear all markers if any
        map.addMarker(
                new MarkerOptions().position(newCity).title(name).snippet(temp)
        );

        map.getUiSettings().setZoomControlsEnabled(true); // (+) (-) zoom control bar
        map.animateCamera(CameraUpdateFactory.newCameraPosition(camera));


    }
}