package com.example.gps_tracking;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SOSActivity extends AppCompatActivity {

    ImageView btnSOS;
    Button btnExit;

    DatabaseReference sosRef;

    boolean isSOSOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        btnSOS = findViewById(R.id.btnSOS);
        btnExit = findViewById(R.id.btnExit);

        sosRef = FirebaseDatabase.getInstance().getReference("gps/xe01/sos");

        PropertyValuesHolder scaleX =
                PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f);

        PropertyValuesHolder scaleY =
                PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f);

        ObjectAnimator pulse =
                ObjectAnimator.ofPropertyValuesHolder(btnSOS, scaleX, scaleY);

        pulse.setDuration(600);
        pulse.setRepeatCount(ValueAnimator.INFINITE);
        pulse.setRepeatMode(ValueAnimator.REVERSE);
        pulse.start();
        btnSOS.setOnClickListener(v -> {

            isSOSOn = !isSOSOn;

            if (isSOSOn) {
                sosRef.setValue(1);
                Toast.makeText(this, "SOS ACTIVATED", Toast.LENGTH_SHORT).show();
            } else {
                sosRef.setValue(0);
                Toast.makeText(this, "SOS OFF", Toast.LENGTH_SHORT).show();
            }
        });

        btnExit.setOnClickListener(v -> {

            sosRef.setValue(0); // tắt SOS trước khi thoát

            Intent intent = new Intent(SOSActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);

            finish();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sosRef.setValue(0);
    }
}