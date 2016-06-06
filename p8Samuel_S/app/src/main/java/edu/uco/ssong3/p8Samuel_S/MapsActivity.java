package edu.uco.ssong3.p8Samuel_S;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends Activity implements OnMapReadyCallback {

    private GoogleMap map;

    private LatLng Biology = new LatLng(35.654895, -97.472264);
    private LatLng Chemistry = new LatLng(35.655048, -97.472822);
    private LatLng ComputerScience = new LatLng(35.653799, -97.472793);
    private LatLng EngineeringPhysics = new LatLng(35.654638, -97.472549);
    private LatLng FuneralServices = new LatLng(35.653574, -97.473658);
    private LatLng Mathematics = new LatLng(35.654049, -97.472869);
    private LatLng Nursing = new LatLng(35.653369, -97.473464);
    private String number = ReceiveActivity.number;
    private String content = ReceiveActivity.content;

    int count = ReceiveActivity.count;

    private Department[] myContacts = {
            new Department("Biology", "4059745623", "http://biology.uco.edu/"),
            new Department("Chemistry", "4059745320", "http://www.uco.edu/cms/chemistry/index.asp"),
            new Department("Computer Science", "4059745716", "http://cs.uco.edu/www/"),
            new Department("Engineering and Physics", "4059745718", "http://www.uco.edu/cms/engineering/index.asp"),
            new Department("Funeral Service", "4059745195", "http://www.uco.edu/cms/funeral/index.asp"),
            new Department("Mathmatics and Statistics", "4059745294", "http://www.math.uco.edu/"),
            new Department("Nursing", "4059745176", "http://www.uco.edu/cms/nursing/index.asp")};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        this.map = map;

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.654236, -97.472939), 18));

        Marker ChemMarker = map.addMarker(new MarkerOptions().position(Biology));
        Marker BioMarker = map.addMarker(new MarkerOptions().position(Chemistry));
        Marker ComMarker = map.addMarker(new MarkerOptions().position(ComputerScience));
        Marker EnginMarker = map.addMarker(new MarkerOptions().position(EngineeringPhysics));
        Marker FuneMarker = map.addMarker(new MarkerOptions().position(FuneralServices));
        Marker MathMarker = map.addMarker(new MarkerOptions().position(Mathematics));
        Marker NursingMarker = map.addMarker(new MarkerOptions().position(Nursing));

        map.getUiSettings().setScrollGesturesEnabled(false);
        map.getUiSettings().setZoomGesturesEnabled(false);


        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getPosition().equals(Biology)) {
                    if (count == 0) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + myContacts[0].getNumber()));
                        startActivity(callIntent);

                    } else {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW);
                        webIntent.setData(Uri.parse(myContacts[0].getWeb()));
                        startActivity(webIntent);

                    }
                } else if (marker.getPosition().equals(Chemistry)) {
                    if (count == 0) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + myContacts[1].getNumber()));
                        startActivity(callIntent);

                    } else {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW);
                        webIntent.setData(Uri.parse(myContacts[1].getWeb()));
                        startActivity(webIntent);

                    }
                } else if (marker.getPosition().equals(ComputerScience)) {
                    if (count == 0) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + myContacts[2].getNumber()));
                        startActivity(callIntent);

                    } else {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW);
                        webIntent.setData(Uri.parse(myContacts[2].getWeb()));
                        startActivity(webIntent);

                    }
                } else if (marker.getPosition().equals(EngineeringPhysics)) {
                    if (count == 0) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + myContacts[3].getNumber()));
                        startActivity(callIntent);

                    } else {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW);
                        webIntent.setData(Uri.parse(myContacts[3].getWeb()));
                        startActivity(webIntent);

                    }
                } else if (marker.getPosition().equals(FuneralServices)) {
                    if (count == 0) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + myContacts[4].getNumber()));
                        startActivity(callIntent);

                    } else {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW);
                        webIntent.setData(Uri.parse(myContacts[4].getWeb()));
                        startActivity(webIntent);

                    }
                } else if (marker.getPosition().equals(Mathematics)) {
                    if (count == 0) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + myContacts[5].getNumber()));
                        startActivity(callIntent);

                    } else {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW);
                        webIntent.setData(Uri.parse(myContacts[5].getWeb()));
                        startActivity(webIntent);

                    }
                } else if (marker.getPosition().equals(Nursing)) {
                    if (count == 0) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + myContacts[6].getNumber()));
                        startActivity(callIntent);

                    } else {
                        Intent webIntent = new Intent(Intent.ACTION_VIEW);
                        webIntent.setData(Uri.parse(myContacts[6].getWeb()));
                        startActivity(webIntent);

                    }
                }
                return true;
            }
        });
    }
}


