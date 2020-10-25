package com.example.memorableplaces;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    List<Address> mapLocations;

    public void centerMapLocation(Location location, String title) {

        if (location != null) {
            LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(userLocation).title(title).draggable(true));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                centerMapLocation(lastLocation, "Your Location");
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
        assert mapFragment != null;
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

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnMapLongClickListener(this);

        Intent intent = getIntent();
        if (intent.getIntExtra("addPlace", 0) == 0) {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // centerMapLocation(location, "Your Location");
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if (lastLocation != null) {
                    centerMapLocation(lastLocation, "Your Location");
                }
            }
        }
        else {
            Location placeLocation = new Location(LocationManager.NETWORK_PROVIDER);
            placeLocation.setLatitude(MainActivity.mapLocations.get(intent.getIntExtra("addPlace", 0)).latitude);
            placeLocation.setLongitude(MainActivity.mapLocations.get(intent.getIntExtra("addPlace", 0)).longitude);
            String title = MainActivity.places.get(intent.getIntExtra("addPlace", 0));
            centerMapLocation(placeLocation, title);
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String mapLocation = "";

        try {

            mapLocations = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (mapLocations != null && mapLocations.size() > 0) {

                if (mapLocations.get(0).getThoroughfare() != null) {
                    if (mapLocations.get(0).getSubThoroughfare() != null) {
                        mapLocation += mapLocations.get(0).getSubThoroughfare() + ", ";
                    }
                    mapLocation += mapLocations.get(0).getThoroughfare();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mapLocation.equals("")) {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-YYYY");
            mapLocation += dateFormat.format(new Date());
        }

        mMap.addMarker(new MarkerOptions().position(latLng).title(mapLocation));
        MainActivity.places.add(mapLocation);
        MainActivity.mapLocations.add(latLng);
        MainActivity.myAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.memorableplaces", MODE_PRIVATE);

        try {

            ArrayList<String> latitudes = new ArrayList<>();
            ArrayList<String> longitudes = new ArrayList<>();

            for (LatLng coord : MainActivity.mapLocations) {
                latitudes.add(Double.toString(coord.latitude));
                longitudes.add(Double.toString(coord.longitude));
            }

            sharedPreferences.edit().putString("places", ObjectSerializer.serialize(MainActivity.places)).apply();
            sharedPreferences.edit().putString("lats", ObjectSerializer.serialize(latitudes)).apply();
            sharedPreferences.edit().putString("lons", ObjectSerializer.serialize(longitudes)).apply();


        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Location Saved!", Toast.LENGTH_SHORT).show();
    }

}