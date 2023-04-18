package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity_Rider_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_profile);

        String pos = getIntent().getStringExtra("POS");

        TextView profHead = findViewById(R.id.profileTV);
        profHead.setText("Rider " + pos);
    }
}