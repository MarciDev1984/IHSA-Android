package com.example.ihsastable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.repository.EventClassRepository;
import com.example.ihsastable.viewmodel.EventClassesViewModel;
import com.example.ihsastable.viewmodel.EventViewModel;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * This is Rider_Order_Activity
 * This is where you are directed upon clicking an item in Fragment_Home
 *
 * Author: Kooper Young, Fisher Reese
 */

public class Activity_Show_Details extends AppCompatActivity
{
    RecyclerView show_details_rv;
    private ArrayList<EventClass> eventClasses;
    private ArrayList<Integer> eventClassIds;
    private EventClassRepository eventClassRepository;
    private RecyclerViewAdapter show_details_rv_adapter;
    private Event event;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        //Get the bundle from the previous activity
        eventClassIds = getIntent().getIntegerArrayListExtra("eventClassIds");
        eventClassRepository = new EventClassRepository();
        show_details_rv = findViewById(R.id.show_details_rv);
        show_details_rv_adapter = new RecyclerViewAdapter("show_details_rv");
        LinearLayoutManager LLM = new LinearLayoutManager(this);
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        show_details_rv.setAdapter(show_details_rv_adapter);
        show_details_rv.setLayoutManager(LLM);
        show_details_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    Observer<ArrayList<EventClass>> eventClassListUpdateObserver = new Observer<ArrayList<EventClass>>() {
        @Override
        public void onChanged(ArrayList<EventClass> eventClasses) {
            show_details_rv_adapter.updateEventClasses();
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        Integer pos = getIntent().getIntExtra("pos",  0);
        Event e = EventViewModel.getModel().eventMutableLiveData.getValue().get(pos);
        eventClassRepository.FetchEventClassesFromEvent(e.getEventClasses());
        EventClassesViewModel.getModel().eventClasses.observe(this, eventClassListUpdateObserver);

        TextView schedHead = findViewById(R.id.scheduleHeaderTV);
        TextView zone = findViewById(R.id.zoneRegion);
        TextView location = findViewById(R.id.showLocation);
        TextView date = findViewById(R.id.showDateTime);

        schedHead.setText(e.getEventName());
        zone.setText(e.getZone() + "");
        location.setText(e.getLocation());
        date.setText(e.getEventTime().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventClassRepository.unsubFirebase();
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            View view = show_details_rv.findChildViewUnder(e.getX(), e.getY());
            if (view != null)
            {
                RecyclerView.ViewHolder holder = show_details_rv.getChildViewHolder(view);

                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    openRider(holder.getAdapterPosition());
                    return true;
                }
            }
            return false;
        }
    }

    public void openRider(int pos)
    {
        Intent opSched = new Intent(this, Activity_Class_Order.class);
        opSched.putExtra("POS", String.valueOf(pos + 1));
        startActivity(opSched);
    }
}