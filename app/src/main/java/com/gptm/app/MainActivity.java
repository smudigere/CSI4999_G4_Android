package com.gptm.app;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.util.CrashUtils;
import com.gptm.app.api.StartRoundApi;
import com.gptm.app.api.WaitTimeApi;
import com.gptm.app.controller.WaitTimeDialogController;
import com.gptm.app.fragments.EnterNamesFragment;
import com.gptm.app.fragments.HomeFragment;
import com.gptm.app.fragments.SelectGolfCourseFragment;
import com.gptm.app.fragments.SelectPlayerCountFragment;
import com.gptm.app.utility.Functions;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Functions.fragment_replacement(
                getSupportFragmentManager(),
                R.id.relative,
                HomeFragment.newInstance(),
                false
        );

        Glide.with(this)
                .load("https://www.fulcrumgallery.com/product-images/P793670-10/golf-antiques.jpg")
                .into((ImageView) findViewById(R.id.background_image_view));

        /*
        WaitTimeDialogController controller = new WaitTimeDialogController(this);
        controller.show();*/

        //Crashlytics.getInstance().crash();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            drawerLayout.openDrawer(Gravity.START);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:    //Home



                break;
            case R.id.item2:    //Store Locator
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }

        drawerLayout.closeDrawer(Gravity.START);
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())   {

            case R.id.play_button:

                Functions.fragment_replacement(
                        getSupportFragmentManager(),
                        R.id.relative,
                        SelectGolfCourseFragment.newInstance(),
                        false
                );

                break;

        }
    }

}