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

/*
 * This is Rider_Order_Activity
 * This is where you are directed upon clicking an item in Fragment_Home
 *
 * Author: Kooper Young, Fisher Reese
 */

public class Activity_Show_Details extends AppCompatActivity
{
    RecyclerView show_details_rv;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

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

        show_details_rv = findViewById(R.id.show_details_rv);

        RecyclerViewAdapter show_details_rv_adapter = new RecyclerViewAdapter("show_details_rv");
        LinearLayoutManager LLM = new LinearLayoutManager(this);
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        show_details_rv.setAdapter(show_details_rv_adapter);
        show_details_rv.setLayoutManager(LLM);
        show_details_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            View view = show_details_rv.findChildViewUnder(e.getX(), e.getY());
            if (view != null)
            {
                RecyclerView.ViewHolder holder = show_details_rv.getChildViewHolder(view);

                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    openRider(holder.getAdapterPosition());
                    return true;
                }
            }
            return false;
        }
    }

    public void openRider(int pos)
    {
        Intent opSched = new Intent(this, Activity_Class_Order.class);
        opSched.putExtra("POS", String.valueOf(pos + 1));
        startActivity(opSched);
    }
}