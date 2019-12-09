package com.example.mylocation;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Boolean checkinlocation = false;
    int z;

    private GoogleMap mMap;

    LocationManager locationManager;
    LocationListener locationListener;





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==1){

            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){


                if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
                {

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                }


                    }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }


    public  void Help(View v){


        if(checkinlocation){

            Intent intent = new Intent(this,Help_Your_Friend.class);

            startActivity(intent);

        }
        else if(z==1) {
            checkinlocation = true;


        }else{

            Toast.makeText(getApplicationContext(),"You are not in At Desired Location",Toast.LENGTH_LONG).show();
        }




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
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                LatLng userlocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(userlocation).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlocation,12));

//                Circle circle = mMap.addCircle(new CircleOptions()
//                        .center(new LatLng(location.getLatitude(),location.getLongitude()))
//                        .radius(100)
//                        .strokeColor(Color.RED)
//                );

                Circle circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(23.6595462,86.4756288))
                        .radius(600)
                        .strokeColor(Color.RED)
                );



                float[] distance = new float[2];

                Location.distanceBetween(location.getLatitude(), location.getLongitude(), circle.getCenter().latitude,circle.getCenter().longitude,distance);

                if ( distance[0] <= circle.getRadius())
                {
                    // Inside The Circle
                    Toast.makeText(getApplicationContext(),"you Are in ",Toast.LENGTH_LONG).show();

                    checkinlocation=true;
                }
//                else if(z==1)
//                {
//                    checkinlocation =true;
//                    // Outside The Circle
//                }


            }
            //Hello india;

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


//            if(z==1) {
//                checkinlocation = true;
//            }


            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,0,locationListener);

            Location lastknownlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                LatLng userlocation = new LatLng(lastknownlocation.getLatitude(), lastknownlocation.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(userlocation).title("Marker in Sydney"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userlocation,16));

                Circle circle = mMap.addCircle(new CircleOptions()
                        .center(new LatLng(23.6595462,86.4756288))
                        .radius(600)
                        .strokeColor(Color.RED)
                );



                float[] distance = new float[2];

                Location.distanceBetween(lastknownlocation.getLatitude(), lastknownlocation.getLongitude(), circle.getCenter().latitude,circle.getCenter().longitude,distance);

                if ( distance[0] <= circle.getRadius())
                {
                    // Inside The Circle
                    Toast.makeText(getApplicationContext(),"you Are in ",Toast.LENGTH_LONG).show();
                    z=1;

                }
                else
                {Toast.makeText(getApplicationContext(),"you Are fucked ",Toast.LENGTH_LONG).show();
                    // Outside The Circle
                }



        }




        // Add a marker in Sydney and move the camera

    }
}
