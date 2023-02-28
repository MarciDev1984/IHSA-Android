package com.example.ihsastable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity)
    {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position)
    {
        switch (position)
        {
            case 0:
                return new scheduleFragment();
            case 1:
                return new fragment2();
            case 2:
                return new fragment3();
            default:
                return new scheduleFragment();
        }
    }

    @Override
    public int getItemCount()
    {
        return 3;
    }
}
