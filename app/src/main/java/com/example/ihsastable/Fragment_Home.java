package com.example.ihsastable;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.repository.EventRepository;
import com.example.ihsastable.viewmodel.EventsViewModel;

import java.util.ArrayList;

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
    private RecyclerView fragment_home_rv;
    RecyclerViewAdapter fragment_home_rv_adapter;
    EventRepository eventRepository;
    ArrayList<Event> events;

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
        fragment_home_rv = view.findViewById(R.id.fragment_home_rv);

        //Create a new adapter instance with a key as an identifier
        //RecyclerViewAdapter fragment_home_rv_adapter = activity.recyclerViewAdapter;
        fragment_home_rv_adapter = new RecyclerViewAdapter("fragment_home_rv");

        //Create a LLM
        LinearLayoutManager LLM = new LinearLayoutManager(view.getContext());

        //Create a gestureDetector
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(view.getContext(), new RecyclerViewOnGestureListener());

        //Set the adapter, LLM, and gestureDetector
        fragment_home_rv.setAdapter(fragment_home_rv_adapter);
        fragment_home_rv.setLayoutManager(LLM);
        eventRepository = new EventRepository();

        fragment_home_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
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
    Observer<ArrayList<Event>> eventListUpdateObserver = new Observer<ArrayList<Event>>() {
        @Override
        public void onChanged(ArrayList<Event> events) {
            fragment_home_rv_adapter.updateEvents();
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        //setup the recycler to use the EventModelClass. Hinted from here https://medium.com/@atifmukhtar/recycler-view-with-mvvm-livedata-a1fd062d2280
        eventRepository.fetchEventsAfterOneYear();
        EventsViewModel.getModel().eventMutableLiveData.observe(getViewLifecycleOwner(), eventListUpdateObserver);
    }
    public void onStop(){
        super.onStop();
        eventRepository.unsubFirebase();
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- onSingleTapConfirmed");
            //Gets the coords of the tap and looks for a child at those coords
            View view = fragment_home_rv.findChildViewUnder(e.getX(), e.getY());

            //If there was a child
            if (view != null)
            {
                Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- onSingleTapConfirmed * child confirmed");
                RecyclerView.ViewHolder holder = fragment_home_rv.getChildViewHolder(view);

                //If the child was the right type
                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- " +
                            "onSingleTapConfirmed * child confirmed * correct type at position: " + holder.getAdapterPosition());
                    openShowDetails(holder.getAdapterPosition());
                    return true;
                }
            }
            return false;
        }
    }

    public void openShowDetails(int pos)
    {
        Intent opSched = new Intent(view.getContext(), Activity_Show_Details.class);
        opSched.putExtra("pos", pos);
        opSched.putIntegerArrayListExtra("eventClassIds", EventsViewModel.getModel().eventMutableLiveData.getValue().get(pos).getEventClasses());
        startActivity(opSched);
    }
}