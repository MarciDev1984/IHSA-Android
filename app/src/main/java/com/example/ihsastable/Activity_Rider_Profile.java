package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Activity_Rider_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_rider_profile);

        final String pos = this.getIntent().getStringExtra("POS");

        final TextView profHead = this.findViewById(R.id.profileTV);
        profHead.setText("Rider " + pos);
    }
}