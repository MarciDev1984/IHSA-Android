package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ihsastable.data.model.Rider;
import com.example.ihsastable.viewmodel.RidersViewModel;

import java.util.ArrayList;

public class Activity_Rider_Profile extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_profile);

        int pos = getIntent().getIntExtra("pos", 0);
        ArrayList<Integer> riderIds = getIntent().getIntegerArrayListExtra("riderIds");

        TextView riderNameTV = findViewById(R.id.RiderNameTV);
        TextView riderIdTV = findViewById(R.id.riderIdTV);
        TextView riderAvgTV = findViewById(R.id.riderAvgTV);
        TextView riderPlaysForTV = findViewById(R.id.riderPlaysForTV);
        // Getting a default rider with 0 should work, otherwise we would be unable to get to this page.
        // But if the activity were destroyed and restarted I don't know if riders would survive.
        Rider rider = RidersViewModel.getModel().riders.getValue().get(pos);

        riderNameTV.setText("Name: " + getIntent().getStringExtra("riderName"));
        riderIdTV.setText("Rider ID: " + getIntent().getIntExtra("riderId", -1));
        riderAvgTV.setText("Rider Average Score: " + getIntent().getIntExtra("riderAvg",-1));
        riderPlaysForTV.setText("Plays For: " + getIntent().getIntExtra("riderPlaysFor",-1));
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}