package com.example.ihsastable;

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

public class scheduleFragment extends Fragment
{
    //Vars for RecyclerView
    private showAdapter showServer;
    private View view;

    public scheduleFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        view = inflater.inflate(R.layout.schedule_fragment, container, false);

        //Recycler view default code (Very fragile)
        showServer = new showAdapter();
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