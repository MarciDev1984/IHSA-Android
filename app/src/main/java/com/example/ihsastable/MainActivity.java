package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.ihsastable.data.repository.EventRepository;
import com.example.ihsastable.data.repository.TestDataRepository;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

/*
* This is MainActivity
* This is where we keep the functionality for the tabbed layout
* and the stuff for the custom appbar.
* It is preferred that no more code than necessary be placed here.
*
* Author: Kooper Young
*/

public class MainActivity extends AppCompatActivity
{
    //Vars for ViewPager
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MaterialToolbar materialToolbar;
    public RecyclerViewAdapter recyclerViewAdapter;
    public EventRepository eventRepository;


    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        //View pager code (Only slightly fragile)
        //https://www.youtube.com/watch?v=pIKdHeOjYNw for references
        this.tabLayout = this.findViewById(R.id.tabLayout);
        this.viewPager = this.findViewById(R.id.viewPager);
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        this.viewPager.setAdapter(viewPagerAdapter);

        this.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(final TabLayout.Tab tab)
            {
                MainActivity.this.viewPager.setCurrentItem(tab.getPosition());
            }

            //Not using these, but afraid to delete
            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(final TabLayout.Tab tab) {}
        });

        //This part is what updates the tabLayout when you swipe, so it knows to animate it
        this.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
        {
            @Override
            public void onPageSelected(final int position)
            {
                super.onPageSelected(position);
                MainActivity.this.tabLayout.getTabAt(position).select();
            }
        });

        //AppBarLayout code
        this.materialToolbar = this.findViewById(R.id.mt);
        this.materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(final MenuItem item)
            {
                Log.d("TAG", "DATA");
                MainActivity.this.openAbout();
                return false;
            }
        });
    }
    //Leads to info page
    public void openAbout(){
        final Intent opAbout = new Intent(this, Activity_About.class);
        this.startActivity(opAbout);
    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        return;
    }
}