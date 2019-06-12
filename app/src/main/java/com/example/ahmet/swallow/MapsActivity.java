package com.example.ahmet.swallow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    int i = 0;
    private String[] locationsEnlem = new String[20];
    private String[] locationsBoylam = new String[20];
    List<Marker> markers = new ArrayList<Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Firebase.setAndroidContext(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);


        for(int i= 0;i<20;i++){

            System.out.println("ahmet");
            System.out.println(i + locationsBoylam[i]);

        }
    }

    @Override
    public void onMapReady(final GoogleMap googleMap){

        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Firebase myFirebase = new Firebase("https://swallow-5c357.firebaseio.com/Posts");

        myFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String enlem = dataSnapshot.child("enlem").getValue(String.class);
                String boylam = dataSnapshot.child("boylam").getValue(String.class);
                locationsEnlem[i] = enlem;
                locationsBoylam[i] = boylam;
                Marker marker = googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(locationsEnlem[i]),
                        Double.parseDouble(locationsBoylam[i])))); //...
                markers.add(marker);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
