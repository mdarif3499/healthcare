package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    VideoView videoView;
    TextView textView;
    int currentPosition;

    private boolean applyExitLogic = true;
    private boolean doubleBackToExitPressedOnce = false;
    private static final int BACK_PRESS_DELAY = 2000; // Time in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        videoView = findViewById(R.id.videoView);
        textView = findViewById(R.id.textView);
        textView.setSelected(true);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        Toast.makeText(getApplicationContext(), "Welcome " + username, Toast.LENGTH_SHORT).show();
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true); // Enable looping if desired
                videoView.start(); // Start the video playback
            }
        });

        CardView exit = findViewById(R.id.cardExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                FirebaseAuth.getInstance().signOut();
                finish(); // Finish the HomeActivity to remove it from the back stack
                applyExitLogic = true; //reset the flag
                startActivity(new Intent(HomeActivity.this, LoginActivity.class).putExtra("applyExitLogic", false));
                Toast.makeText(HomeActivity.this, "Log Out Success", Toast.LENGTH_SHORT).show();
            }
        });

        CardView findDoctor = findViewById(R.id.cardFindDoctor);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FindDoctorActivity.class));
            }
        });

        CardView labTest = findViewById(R.id.cardLabTest);
        labTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, LabTestActivity.class));
            }
        });

        CardView orderDetails = findViewById(R.id.cardOrderDetails);
        orderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OrderDetailsActivity.class));
            }
        });

        CardView buyMedicine = findViewById(R.id.cardBuyMedicine);
        buyMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));
            }
        });

        CardView health = findViewById(R.id.cardHealthDoctor);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthArticlesActivity.class));
            }
        });
        CardView e = findViewById(R.id.emergency);
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, Emergency.class));
                Toast.makeText(HomeActivity.this, "Emergency Service", Toast.LENGTH_SHORT).show();
            }
        });
        CardView OD = findViewById(R.id.doctor);
        OD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OnlineDoctor.class));
                Toast.makeText(HomeActivity.this, "Telemedicine Service", Toast.LENGTH_SHORT).show();
            }
        });
        CardView about = findViewById(R.id.us);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AboutUs.class));
                Toast.makeText(HomeActivity.this, "Our Profile", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onPause() {
        super.onPause();
        currentPosition = videoView.getCurrentPosition(); // Save the current position
        videoView.pause(); // Pause the video
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.seekTo(currentPosition); // Resume from the saved position
        videoView.start(); // Start video playback
    }

    @Override
    public void onBackPressed() {
        //applyExitLogic = true;
        if (applyExitLogic) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }
}