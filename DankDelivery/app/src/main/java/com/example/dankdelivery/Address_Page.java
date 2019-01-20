package com.example.dankdelivery;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.Dialog;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import android.Manifest.permission;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.support.v4.app.ActivityCompat;
import android.support.annotation.NonNull;

public class Address_Page extends AppCompatActivity {

    private static final String TAG = "Address_Page";

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;

    public static final String EXTRA_TEXT = "com.example.application.example.EXTRA_TEXT";
    //variables
    private boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    //private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign in");

        configureNextButton();
        getLocationPermission();

    }
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting device's current location");

    }

    private void configureNextButton() {
        Button nextButton = (Button) findViewById(R.id.button_FindStrainsNearYou);
        Button sellerButton = (Button) findViewById(R.id.sellerButton);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAddress();
            }
        });

        sellerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Address_Page.this, SellerSignIn.class));
            }
        });
    }

    private void getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permission");
        String[] permissions = {FINE_LOCATION, COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(),
                        COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mLocationPermissionGranted = true;
            initMap();
        }
        else{
            ActivityCompat.requestPermissions(this,
                    permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults){
        Log.d(TAG, "onRequestPermissionsResult: called");
        mLocationPermissionGranted = false;

        switch(requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:
                if(grantResults.length > 0 ){
                    for(int i = 0; i < grantResults.length; i++){
                        if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: Permission failed");
                            break;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: Permission granted");
                    mLocationPermissionGranted = true;
                    //THEN WE CAN INITIALIZE OUR MAP
                }
        }
    }

    /* This method checks if the phone has Google Services
     * enabled on the phone
     */
    public boolean isServicesOk(){
        Log.d(TAG, "isServicesOK: checking google services version");
        int available = GoogleApiAvailability.getInstance().
                isGooglePlayServicesAvailable(Address_Page.this);

        //If it works
        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can create map requests
            return true;
        }
        //If the user can fix it themselves
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG, "isServiceOk: An error occurred, but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().
                    getErrorDialog(Address_Page.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }
        //If the user could not make any changes, then they cannot use the map
        else {
            Toast.makeText(this,
                    "We cannot make map request, please try again later.",
                    Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "map is ready");
                mMap = googleMap;
            }
        });
    }
    public void sendAddress(){
        EditText editText1 = (EditText) findViewById(R.id.text_Address);
        String text = editText1.getText().toString();

        Intent intent = new Intent(this, StrainsNearYou.class);
        intent.putExtra(EXTRA_TEXT, text);
        startActivity(intent);
            }
}

