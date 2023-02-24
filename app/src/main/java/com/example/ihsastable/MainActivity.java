package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
{
    //Vars for RecyclerView
    private showAdapter showServer;

    //Vars for ViewPager
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recyler view default code (Very fragile)
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

        //View pager code (Only slightly fragile)
        //https://www.youtube.com/watch?v=pIKdHeOjYNw for references
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    //This class handles taps on the recycler view items
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
}