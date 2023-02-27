package com.example.ihsastable;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class scheduleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        String pos = getIntent().getStringExtra("POS");

        TextView schedHead = findViewById(R.id.scheduleHeaderTV);
        schedHead.setText("Show " + pos + " Schedule");
    }
}