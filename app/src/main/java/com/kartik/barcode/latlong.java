package com.kartik.barcode;

import android.*;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Kartik on 14-07-2016.
 */
public class latlong extends AppCompatActivity implements LocationListener
{
    LocationManager locationmanager;
    TextView tv;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth fauth;
    String j;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.latlon);
        locationmanager = (LocationManager) getSystemService(LOCATION_SERVICE);
        tv = (TextView) findViewById(R.id.textView40);
        database = FirebaseDatabase.getInstance();
        fauth = FirebaseAuth.getInstance();
        reference = database.getReference();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Criteria cr = new Criteria();
        cr.setAccuracy(Criteria.NO_REQUIREMENT);
        String g=fauth.getCurrentUser().getUid();

        reference.child(g).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
             add a=  dataSnapshot.getValue(add.class);
                j=a.status;

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
        String provider = locationmanager.getBestProvider(cr, false);
            if(j.equals("active"))
            {
                locationmanager.requestLocationUpdates(provider, 10000, 100, this);
            }
    }

    @Override
    public void onLocationChanged(Location location)
    {
        Double w=location.getLongitude();
        Double d=location.getLatitude();
        Log.e("key",w+" "+d);
        tv.setText(""+w+"  "+d);
        Toast.makeText(getApplicationContext(), location.toString(), Toast.LENGTH_LONG).show();
        new geocode().execute(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }

    class geocode extends AsyncTask<Location, String, Boolean>
    {
        @Override
        protected Boolean doInBackground(Location... params)
        {
            params[0].getLatitude();
            params[0].getLongitude();

            Geocoder coder = new Geocoder(latlong.this, Locale.getDefault());
            try
            {
                List<Address> addresses = coder.getFromLocation(params[0].getLatitude(), params[0].getLongitude(), 3);
                Address address = addresses.get(0);
                address.getAddressLine(0);

                String line1 = address.getAddressLine(0);
                String line2 = address.getAddressLine(1);
                String line3 = address.getAddressLine(2);

                publishProgress();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            tv.setText(values[0]);
            super.onProgressUpdate(values);
        }
    }
}