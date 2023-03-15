package com.example.ihsastable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View pager code (Only slightly fragile)
        //https://www.youtube.com/watch?v=pIKdHeOjYNw for references
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            //Not using these, but afraid to delete
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        //This part is what updates the tabLayout when you swipe, so it knows to animate it
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback()
        {
            @Override
            public void onPageSelected(int position)
            {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

        //AppBarLayout code
        materialToolbar = findViewById(R.id.mt);
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                Log.d("TAG", "DATA");
                openAbout();
                return false;
            }
        });
    }
    //Leads to info page
    public void openAbout(){
        Intent opAbout = new Intent(this, AboutPage.class);
        startActivity(opAbout);
    }
}