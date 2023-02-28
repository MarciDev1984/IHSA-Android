package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class riderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);
        
        String pos = getIntent().getStringExtra("POS");

        TextView schedHead = findViewById(R.id.classTV);
        schedHead.setText("Class " + pos + " Order");
    }
}