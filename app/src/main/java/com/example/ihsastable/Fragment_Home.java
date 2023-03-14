package com.example.ihsastable;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/*
 * This is Fragment_Home
 * This is the first tab in the tabbed layout
 * This contains the upcoming shows
 * Clicking a show will take you to scheduleActivity
 *
 * Author: Kooper Young
 */

public class Fragment_Home extends Fragment
{
    //Vars for RecyclerView
    private showAdapter showServer;
    private View view;

    public Fragment_Home() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //Recycler view default code (Very fragile)
        showServer = new showAdapter(0);
        RecyclerView showRecycler = view.findViewById(R.id.showsRV);
        showRecycler.setAdapter(showServer);

        LinearLayoutManager myManager = new LinearLayoutManager(getActivity());
        showRecycler.setLayoutManager(myManager);

        GestureDetectorCompat detector = new GestureDetectorCompat(getActivity(), new RecyclerViewOnGestureListener());
        showRecycler.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
            {
                return detector.onTouchEvent(e);
            }
        });

        return view;
    }

    //This class handles taps on the recycler view items
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            RecyclerView showRV = view.findViewById(R.id.showsRV);
            View view = showRV.findChildViewUnder(e.getX(), e.getY());
            Log.d("click", "click happened in frag1");

            if (view != null)
            {
                RecyclerView.ViewHolder holder = showRV.getChildViewHolder(view);

                if (holder instanceof showAdapter.showViewHolder)
                {
                    int position = holder.getAdapterPosition();
                    Log.d("click", "single tap clicked on item " + position);
                    openSchedule(position);
                    Log.d("click", "Going to show");
                    return true;
                }
            }
            return false;
        }
    }

    public void openSchedule(int pos){
        Intent opSched = new Intent(view.getContext(), scheduleActivity.class);
        opSched.putExtra("POS", String.valueOf(pos + 1));
        startActivity(opSched);
    }
}