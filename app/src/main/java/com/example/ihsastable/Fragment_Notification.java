package com.example.ihsastable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * This is Fragment_Notification
 * This contains nothing at the moment
 * but will contain a RV for past notifications and announcements
 *
 * Author: Kooper Young
 */

public class Fragment_Notification extends Fragment
{
    public Fragment_Notification() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }
}