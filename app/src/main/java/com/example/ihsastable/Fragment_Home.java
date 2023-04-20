package com.example.ihsastable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.repository.EventRepository;
import com.example.ihsastable.viewmodel.EventViewModel;

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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState)
    {
        final MainActivity activity = (MainActivity) this.getContext();
        //getActivity() or getContext() don't work in fragments.
        //This is the work-around
        //You can do view.getContext() if you need that
        this.view = inflater.inflate(R.layout.fragment_home, container, false);

        //Bind the RV object to the XML
        this.fragment_home_rv = this.view.findViewById(R.id.fragment_home_rv);

        //Create a new adapter instance with a key as an identifier
        //RecyclerViewAdapter fragment_home_rv_adapter = activity.recyclerViewAdapter;
        this.fragment_home_rv_adapter = new RecyclerViewAdapter("fragment_home_rv");

        //Create a LLM
        final LinearLayoutManager LLM = new LinearLayoutManager(this.view.getContext());

        //Create a gestureDetector
        final GestureDetectorCompat gestureDetector = new GestureDetectorCompat(this.view.getContext(), new RecyclerViewOnGestureListener());

        //Set the adapter, LLM, and gestureDetector
        this.fragment_home_rv.setAdapter(this.fragment_home_rv_adapter);
        this.fragment_home_rv.setLayoutManager(LLM);
        this.eventRepository = new EventRepository();

        this.fragment_home_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            //This is the first thing called on a tap, it passes it to the RecyclerViewOnGestureListener
            @Override
            public boolean onInterceptTouchEvent(@NonNull final RecyclerView recyclerView, @NonNull final MotionEvent motionEvent)
            {
                Log.d("RV GESTURE", "Fragment_Home --- onCreateView -- onInterceptTouchEvent");
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        this.view.findViewById(R.id.refreshBTN).setOnClickListener(view -> this.fragment_home_rv_adapter.notifyDataSetChanged());

        return this.view;
    }
    Observer<ArrayList<Event>> eventListUpdateObserver = new Observer<ArrayList<Event>>() {
        @Override
        public void onChanged(final ArrayList<Event> events) {
            Fragment_Home.this.fragment_home_rv_adapter.updateEvents();
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        //setup the recycler to use the EventModelClass. Hinted from here https://medium.com/@atifmukhtar/recycler-view-with-mvvm-livedata-a1fd062d2280
        this.eventRepository.fetchEventsAfterOneYear();
        EventViewModel.getModel().eventMutableLiveData.observe(this.getViewLifecycleOwner(), this.eventListUpdateObserver);
    }
    public void onStop(){
        super.onStop();
        this.eventRepository.unsubFirebase();
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(final MotionEvent e)
        {
            Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- onSingleTapConfirmed");
            //Gets the coords of the tap and looks for a child at those coords
            final View view = Fragment_Home.this.fragment_home_rv.findChildViewUnder(e.getX(), e.getY());

            //If there was a child
            if (view != null)
            {
                Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- onSingleTapConfirmed * child confirmed");
                final RecyclerView.ViewHolder holder = Fragment_Home.this.fragment_home_rv.getChildViewHolder(view);

                //If the child was the right type
                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    Log.d("RV GESTURE", "Fragment_Home --- RecyclerViewOnGestureListener -- " +
                            "onSingleTapConfirmed * child confirmed * correct type at position: " + holder.getAdapterPosition());
                    Fragment_Home.this.openShowDetails(holder.getAdapterPosition());
                    return true;
                }
            }
            return false;
        }
    }

    public void openShowDetails(final int pos)
    {
        final Intent opSched = new Intent(this.view.getContext(), Activity_Show_Details.class);
        opSched.putExtra("pos", pos);
        opSched.putIntegerArrayListExtra("eventClassIds", EventViewModel.getModel().eventMutableLiveData.getValue().get(pos).getEventClasses());
        this.startActivity(opSched);
    }
}