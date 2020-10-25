package com.example.hikingtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocation(location);
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

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else
        {
            locationManager.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lastLocation != null)
                updateLocation(lastLocation);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }

    @SuppressLint("SetTextI18n")
    public void updateLocation(Location location) {

        TextView latTextView = findViewById(R.id.latTextView);
        TextView longTextView = findViewById(R.id.longTextView);
        TextView altTextView = findViewById(R.id.altTextView);
        TextView accuracyTextView = findViewById(R.id.accuracyTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);

        DecimalFormat formatter = new DecimalFormat("0.0000");

        latTextView.setText("Latitude : " + formatter.format(location.getLatitude()));
        longTextView.setText("Longitude : " + formatter.format(location.getLongitude()));
        altTextView.setText("Altitude : " + location.getAltitude());
        accuracyTextView.setText("Accuracy : " + Double.toString(location.getAccuracy()));

        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        String address = "Could not find Address :(";
        try {
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (addressList != null && addressList.size() > 0)
            {
                address = "";

//                if (addressList.get(0).getAddressLine(0) != null) {
//                    Log.i("Address", addressList.get(0).getAddressLine(0));
//                }

                if (addressList.get(0).getCountryName() != null) {
                    address += addressList.get(0).getCountryName() + ", ";
                }

                if (addressList.get(0).getAdminArea() != null) {
                    address += addressList.get(0).getAdminArea() + "\n";
                }

                if (addressList.get(0).getLocality() != null) {
                    address += addressList.get(0).getLocality() + ", ";
                }

                if (addressList.get(0).getThoroughfare() != null) {
                    address += addressList.get(0).getThoroughfare() + "\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        resultTextView.setText(address);
    }
}
