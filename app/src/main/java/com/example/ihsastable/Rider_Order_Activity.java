package com.example.ihsastable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Rider_Order_Activity extends AppCompatActivity
{
    RecyclerView rider_order_rv;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_order);

        //Get the bundle from the previous activity
        String pos = getIntent().getStringExtra("POS");

        TextView schedHead = findViewById(R.id.scheduleHeaderTV);
        System.out.println(pos);
        if(pos.equals("1") )
        {
            schedHead.setText("Butler Equestrian");
        }
        else{
            schedHead.setText("Show " + pos + " Schedule");
        }

        //Bind the RV object to the XML
        rider_order_rv = findViewById(R.id.rider_order_rv);

        //Create a new adapter instance with a key as an identifier
        RecyclerViewAdapter rider_order_rv_adapter = new RecyclerViewAdapter("rider_order_rv");

        //Create a LLM // was getActivity()
        LinearLayoutManager LLM = new LinearLayoutManager(this);

        //Create a gestureDetector
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        //Set the adapter, LLM, and gestureDetector
        rider_order_rv.setAdapter(rider_order_rv_adapter);
        rider_order_rv.setLayoutManager(LLM);
        rider_order_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            //This is the first thing called on a tap, it passes it to the RecyclerViewOnGestureListener
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                Log.d("RV GESTURE", "Rider_Order_Activity --- onCreateView -- onInterceptTouchEvent");
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            Log.d("RV GESTURE", "Rider_Order_Activity --- RecyclerViewOnGestureListener -- onSingleTapConfirmed");
            //Gets the coords of the tap and looks for a child at those coords
            View view = rider_order_rv.findChildViewUnder(e.getX(), e.getY());

            //If there was a child
            if (view != null)
            {
                Log.d("RV GESTURE", "Rider_Order_Activity --- RecyclerViewOnGestureListener -- onSingleTapConfirmed * child confirmed");
                RecyclerView.ViewHolder holder = rider_order_rv.getChildViewHolder(view);

                //If the child was the right type
                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    Log.d("RV GESTURE", "Rider_Order_Activity --- RecyclerViewOnGestureListener -- " +
                            "onSingleTapConfirmed * child confirmed * correct type at position: " + holder.getAdapterPosition());
                    openRider(holder.getAdapterPosition());
                    return true;
                }
            }
            return false;
        }
    }

    public void openRider(int pos)
    {
        Intent opSched = new Intent(this, riderActivity.class);
        opSched.putExtra("POS", String.valueOf(pos + 1));
        startActivity(opSched);
    }
}