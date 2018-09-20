package com.gptm.app;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gptm.app.fragments.Login;
import com.gptm.app.fragments.SignUp;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private FusedLocationProviderClient client;

    /**
     *  Perform initialization of all fragments and loaders.
     *
     * @param savedInstanceState    <p> Bundle. </p>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        client = LocationServices.getFusedLocationProviderClient(this);
        showSignUpLogin();
    }


    /*  ********************************************************************************    */
    //setContentView is invoked to display a Login or SignUp page.
    private Login login;
    private SignUp signUp;

    /**
     * Once the user agrees to terms and conditions and reads through it,
     * the layout is switched to display Login or SignUpPage.
     */
    private void showSignUpLogin()  {

        setContentView(R.layout.activity_login_sign_up);

        login = new Login();
        signUp = new SignUp();

        fragment_replacement(login);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())  {

                    case 0:
                        fragment_replacement(login);    //Takes the user to login page (or fragment).
                        break;
                    case 1:
                        fragment_replacement(signUp);   //Takes the user to signUp page.
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }


    /**
     * Method to replace fragments between {@link Login} and {@link SignUp}.
     *
     * @param fragment  The fragment that has to be replaced.
     */
    private void fragment_replacement(Fragment fragment)    {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relative, fragment)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean(getString(R.string.LOCATION_PERMISSION), true))
            getMyLocation();
    }

    /**
     * Upon invoking this function, the function checks if access to Location has already been granted (older version).
     * If not, this function invokes another function to open the OS permissions for Location.
     */
    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.


            //------------------------------------------------------------------------------
            ActivityCompat.requestPermissions(LoginActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            return;
        }

        client.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                    }
                });
    }


    /**
     *  Callback for the result from requesting permissions.
     *
     * @param requestCode   <p>  The request code passed in
     *                      requestPermissions(android.app.Activity, String[], int). </p>
     * @param permissions   <p> The requested permissions. Never null. </p>
     * @param grantResults  <p> The grant results for the corresponding permissions which is either
     *                      PERMISSION_GRANTED or PERMISSION_DENIED. Never null. </p>
     */
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getMyLocation();

                } else {
                    prefs.edit().putBoolean(getString(R.string.LOCATION_PERMISSION), false)
                            .apply();
                }
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}