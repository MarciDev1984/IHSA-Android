package com.example.ihsastable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
    private View view;
    private RecyclerView show_schedule_rv;

    //Required empty constructor for fragments
    public Fragment_Home() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        MainActivity activity = (MainActivity) getContext();
        //getActivity() or getContext() don't work in fragments.
        //This is the work-around
        //You can do view.getContext() if you need that
        view = inflater.inflate(R.layout.fragment_home, container, false);

        //Bind the RV object to the XML
        show_schedule_rv = view.findViewById(R.id.show_schedule_rv);

        //Create a new adapter instance with a key as an identifier
<<<<<<< Updated upstream
        RecyclerViewAdapter show_schedule_rv_adapter = new RecyclerViewAdapter("show_schedule_rv");
=======
        RecyclerViewAdapter fragment_home_rv_adapter = activity.recyclerViewAdapter;
>>>>>>> Stashed changes

        //Create a LLM // was getActivity()
        LinearLayoutManager LLM = new LinearLayoutManager(view.getContext());

        //Create a gestureDetector
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(view.getContext(), new RecyclerViewOnGestureListener());

        //Set the adapter, LLM, and gestureDetector
        show_schedule_rv.setAdapter(show_schedule_rv_adapter);
        show_schedule_rv.setLayoutManager(LLM);
        show_schedule_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            //This is the first thing called on a tap, it passes it to the RecyclerViewOnGestureListener
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                Log.d("RV GESTURE", "Fragment_Home --- onCreateView -- onInterceptTouchEvent");
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        return view;
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- onSingleTapConfirmed");
            //Gets the coords of the tap and looks for a child at those coords
            View view = show_schedule_rv.findChildViewUnder(e.getX(), e.getY());

            //If there was a child
            if (view != null)
            {
                Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- onSingleTapConfirmed * child confirmed");
                RecyclerView.ViewHolder holder = show_schedule_rv.getChildViewHolder(view);

                //If the child was the right type
                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- " +
                            "onSingleTapConfirmed * child confirmed * correct type at position: " + holder.getAdapterPosition());
                    openSchedule(holder.getAdapterPosition());
                    return true;
                }
            }
            return false;
        }
    }

    public void openSchedule(int pos)
    {
<<<<<<< Updated upstream
        Intent opSched = new Intent(view.getContext(), Rider_Order_Activity.class);
        opSched.putExtra("POS", String.valueOf(pos + 1));
        startActivity(opSched);
=======
//        Intent opSched = new Intent(view.getContext(), Activity_Show_Details.class);
//        opSched.putExtra("POS", String.valueOf(pos + 1));
//        startActivity(opSched);
>>>>>>> Stashed changes
    }
}