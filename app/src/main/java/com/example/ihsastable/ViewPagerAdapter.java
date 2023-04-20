package com.example.ihsastable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/*
 * This is ViewPageAdapter
 * This is the class that gives the tabbed functionality to the ViewPager in MainActivity
 *
 * Author: Kooper Young
 */

public class ViewPagerAdapter extends FragmentStateAdapter
{

    public ViewPagerAdapter(@NonNull final FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(final int position)
    {
        switch (position)
        {
            case 1:
                return new Fragment_Notification();
            case 2:
                return new Fragment_Favorite();
            default:
                return new Fragment_Home();
        }
    }

    @Override
    public int getItemCount()
    {
        return 3;
    }
}
