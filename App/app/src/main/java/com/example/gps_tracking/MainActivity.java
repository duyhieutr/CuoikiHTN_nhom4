package com.example.gps_tracking;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ImageView imgMapPreview;
    LinearLayout btnLive;
    LinearLayout btnSOS;
    Switch switchEmergency;

    DatabaseReference sosRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        imgMapPreview = findViewById(R.id.imgMapPreview);
        btnLive = findViewById(R.id.btnLive);
        btnSOS = findViewById(R.id.btnSOS);
        switchEmergency = findViewById(R.id.switchEmergency);

        // node Firebase cho SOS
        sosRef = FirebaseDatabase.getInstance().getReference("gps/xe01/sos");

        imgMapPreview.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MapActivity.class));
        });

        btnLive.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MapActivity.class));
        });

        btnSOS.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SOSActivity.class));
        });

        switchEmergency.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                sosRef.setValue(1);
            } else {
                sosRef.setValue(0);
            }
        });
    }
}