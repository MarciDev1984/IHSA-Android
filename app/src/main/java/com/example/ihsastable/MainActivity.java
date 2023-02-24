package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            RecyclerView showRV = findViewById(R.id.showsRV);
            View view = showRV.findChildViewUnder(e.getX(), e.getY());
            Log.d("click", "click happened");

            if (view != null)
            {
                RecyclerView.ViewHolder holder = showRV.getChildViewHolder(view);

                if (holder instanceof showAdapter.showViewHolder)
                {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "single tap clicked on item " + position);
                    //Intent goToNextActivity = new Intent(getApplicationContext(), testActivity.class);
                    //startActivity(goToNextActivity);
                    Log.d("click", "Going to show");
                    return true;
                }
            }
            return false;
        }
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onSingleTapConfirmed(MotionEvent e) {
            RecyclerView showRV = findViewById(R.id.showsRV);
            View view = showRV.findChildViewUnder(e.getX(), e.getY());
            Log.d("click", "click happened");

            if (view != null) {
                RecyclerView.ViewHolder holder = showRV.getChildViewHolder(view);

                if (holder instanceof showAdapter.showViewHolder) {
                    int position = holder.getAdapterPosition();

                    Log.d("click", "single tap clicked on item " + position);
                    //Intent goToNextActivity = new Intent(getApplicationContext(), testActivity.class);
                    //startActivity(goToNextActivity);
                    Log.d("click", "Going to show");
                    return true;
                }
            }
            return false;
        }
    }

    private showAdapter showServer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showServer = new showAdapter();
        RecyclerView showRecycler = findViewById(R.id.showsRV);
        showRecycler.setAdapter(showServer);

        LinearLayoutManager myManager = new LinearLayoutManager(this);
        showRecycler.setLayoutManager(myManager);

        GestureDetectorCompat detector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());
        showRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
            {
                return detector.onTouchEvent(e);
            }
        });
    }
}