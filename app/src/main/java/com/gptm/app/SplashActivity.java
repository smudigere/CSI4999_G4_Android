package com.gptm.app;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;


public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);    // Removes title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);    // Removes notification bar

        handler = new Handler();
        intent = new Intent(getApplicationContext(), MainActivity.class);

        try {

            setContentView(R.layout.activity_splash);

            Glide.with(this)
                    .load("https://images5.alphacoders.com/671/671148.jpg")
                    .into((ImageView) findViewById(R.id.background_image_view));
            // Start timer and launch main activity
            IntentLauncher launcher = new IntentLauncher();
            launcher.start();
        } catch (Error error) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            this.finish();
        }
    }

    private class IntentLauncher extends Thread {

        /**
         * Sleep for some time and than start new activity.
         */
        @Override
        public void run() {
            try {
                // Sleeping
                long SLEEP_TIME = 3;
                Thread.sleep(SLEEP_TIME *1000);
            } catch (Exception ignored) {}

            // Start MainActivity
            SplashActivity.this.startActivity(intent);

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SplashActivity.this.finish();
                }
            }, 500);
        }
    }
}
