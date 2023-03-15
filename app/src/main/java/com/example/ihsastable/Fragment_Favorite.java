package com.example.ihsastable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * This is Fragment_Favorite
 * This is where the riders you have chose to follow will be displayed
 *
 * Author: Kooper Young
 */

public class Fragment_Favorite extends Fragment
{
    public Fragment_Favorite() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }
}