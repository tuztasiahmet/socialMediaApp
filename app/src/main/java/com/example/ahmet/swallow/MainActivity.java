package com.example.ahmet.swallow;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private Button mapButton;

    final List<Post> posts = new ArrayList<Post>();
    BottomNavigationView navigation;


    private void requestPermission(){ // konumu kullanabilmek için izin alır.
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mapButton = findViewById(R.id.mapBtn);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        Button dmBtn = findViewById(R.id.directMessageBtn);
        dmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Users.class);
                startActivity(intent);
            }
        });
        Firebase.setAndroidContext(this);
        Firebase myFirebase = new Firebase("https://swallow-5c357.firebaseio.com/Posts");

        myFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String boylam = dataSnapshot.child("boylam").getValue(String.class);
                String enlem = dataSnapshot.child("enlem").getValue(String.class);
                String eser = dataSnapshot.child("eser").getValue(String.class);
                String yorum = dataSnapshot.child("userName").getValue(String.class);
                String username = dataSnapshot.child("yorum").getValue(String.class);
                posts.add(new Post(boylam, enlem,eser,username,yorum));

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

        final ListView listView = (ListView) findViewById(R.id.listmain);
        CustomAdapter adapter = new CustomAdapter(this, posts);
        listView.setAdapter(adapter);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(navigationTiklama);


        FragmentTransaction fragmentiCagir =
                getSupportFragmentManager().beginTransaction();
        fragmentiCagir.replace(R.id.burayaCagir, new HomeFragment());
        fragmentiCagir.commit();

        requestPermission();

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            LocationManager mLocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
            final MyLocationListener locListener = new MyLocationListener();
            mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        }

    }
    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {

            String tempEnlem,tempBoylam;
            tempEnlem = Double.toString(loc.getLatitude());
            tempBoylam = Double.toString(loc.getLongitude());

            String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

            DatabaseReference current_location_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Location");

            Map newLoc = new HashMap();
            newLoc.put("enlem", tempEnlem);
            newLoc.put("boylam", tempBoylam);

            current_location_db.setValue(newLoc);

        }
        public void onProviderDisabled(String arg0) {

        }
        public void onProviderEnabled(String provider) {

        }
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }
BottomNavigationView.OnNavigationItemSelectedListener navigationTiklama=
        new BottomNavigationView.OnNavigationItemSelectedListener()  {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment fragment = null;

                ListView list = findViewById(R.id.listmain);
                Button mapBtn = findViewById(R.id.mapBtn);
                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        list.setVisibility(View.VISIBLE);
                        mapBtn.setVisibility(View.VISIBLE);
                        fragment = new HomeFragment();

                        break;

                    case R.id.navigation_notifications:
                        list.setVisibility(View.GONE);
                        mapBtn.setVisibility(View.GONE);
                        fragment = new NotificationFragment();
                        break;


                    case R.id.navigation_dashboard:
                        list.setVisibility(View.GONE);
                        mapBtn.setVisibility(View.GONE);
                        fragment = new PostFragment();
                        break;

                    case R.id.navigation_direct_message:
                        list.setVisibility(View.GONE);
                        mapBtn.setVisibility(View.GONE);
                        fragment = new DMFragment();
                        break;

                    case R.id.navigation_user:
                        list.setVisibility(View.GONE);
                        fragment = new ProfileFragment();
                        break;


                }
                FragmentTransaction fragmentiCagir =
                        getSupportFragmentManager().beginTransaction();
                fragmentiCagir.replace(R.id.burayaCagir,fragment);

                fragmentiCagir.commit();
                return true;
            }

        };

}
