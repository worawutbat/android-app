package th.ac.ku.myandroidapp;


import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private String currentPoint = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if(intent.getStringExtra("point").equals(currentPoint)){
            Toast.makeText(this, "nc", Toast.LENGTH_SHORT);
        }
        else {
            String p = intent.getStringExtra("point");
            Log.i("Abc", " c");
            Toast.makeText(this, "c", Toast.LENGTH_SHORT);

        }
        Toast.makeText(this, "ccc", Toast.LENGTH_SHORT);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final LatLng LH, wBridge,threeMasters;

        // Add a marker in Sydney and move the camera
        wBridge = new LatLng(13.854720, 100.568888);
        LH = new LatLng(13.847234, 100.571503);
        threeMasters = new LatLng(13.842223, 100.573112);
        MarkerOptions aa = new MarkerOptions().position(wBridge).title("สะพานขาว");

        mMap.addMarker(aa);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(wBridge));

        mMap.addMarker(new MarkerOptions().position(LH).title("LH 2,3,4"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(LH));

        mMap.addMarker(new MarkerOptions().position(threeMasters).title("3 Masters"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(threeMasters));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 0);
            // here to request the missing permissions, and then overriding
//               public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                                      int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        else{
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                String name = marker.getTitle();
                String no = "0";

                if(name.equals("3 Masters")) {
                    no = "1";
                }
                else if (name.equals("สะพานขาว")){
                    no = "2";
                }
                else if(name.equals("LH 2,3,4")){
                    no = "3";
                }
                Log.i("Abc", " ccc");
//                Intent intent = new Intent(MapsActivity.this, MiniGameAtXXX.class);
//                intent.putExtra("choice", no+","+currentPoint);
//                startActivity(intent);
                return true;
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == 0){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mMap.setMyLocationEnabled(true);
            }
        }
    }
}

