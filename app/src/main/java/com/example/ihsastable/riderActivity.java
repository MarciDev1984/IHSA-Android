package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class riderActivity extends AppCompatActivity {

    private RecyclerViewAdapter orderRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SUPER AMAZING TEMP", "yeah its rider activity");

        setContentView(R.layout.activity_rider);
        
        String pos = getIntent().getStringExtra("POS");

        TextView schedHead = findViewById(R.id.classTV);
        schedHead.setText("Class " + pos + " Order");

        orderRV = new RecyclerViewAdapter("rider_order_rv");
        RecyclerView riderRecycler = findViewById(R.id.orderRV);
        riderRecycler.setAdapter(orderRV);

        LinearLayoutManager thisManager = new LinearLayoutManager(this);
        riderRecycler.setLayoutManager(thisManager);

        GestureDetectorCompat detect = new GestureDetectorCompat(this, new riderActivity.RecyclerViewOnGestureListener());
        riderRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener(){
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e){
                return detect.onTouchEvent(e);
            }
        });
    }

    public void openSingleRider(int pos){
        Intent opSched = new Intent(this, riderProfile.class);
        opSched.putExtra("POS", String.valueOf(pos + 1));
        startActivity(opSched);
    }

    public void openPDF(View v) {
        URL pdfLink;
        Uri pdfURI;
        try {
            pdfLink = new URL("https://assets.website-files.com/603d0d2db8ec32ba7d44fffe/603d0e327eb2748c8ab1053f_loremipsum.pdf");
            pdfURI = Uri.parse(pdfLink.toURI().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Unknown Error Occurred!", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("URI", String.valueOf(pdfURI));
        startActivity(new Intent(Intent.ACTION_VIEW, pdfURI));
    }

    //This is almost directly Jacob Pickman's implementation
    //-----
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            RecyclerView orderRV = findViewById(R.id.orderRV);
            View view = orderRV.findChildViewUnder(e.getX(), e.getY());
            Log.d("click", "click happened");

            if (view != null)
            {
                RecyclerView.ViewHolder holder = orderRV.getChildViewHolder(view);

                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "single tap clicked on item " + position);
                    openSingleRider(position);
                    Log.d("click", "Going to show");
                    return true;
                }
            }
            return false;
        }
    }
    //----
}