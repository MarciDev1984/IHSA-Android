package com.example.ihsastable;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.ihsastable.viewmodel.EventsViewModel;

import java.util.ArrayList;

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
    public void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_show_details);
        //Get the bundle from the previous activity
        this.eventClassIds = this.getIntent().getIntegerArrayListExtra("eventClassIds");
        this.eventClassRepository = new EventClassRepository();
        this.show_details_rv = this.findViewById(R.id.show_details_rv);
        this.show_details_rv_adapter = new RecyclerViewAdapter("show_details_rv");
        final LinearLayoutManager LLM = new LinearLayoutManager(this);
        final GestureDetectorCompat gestureDetector = new GestureDetectorCompat(this, new RecyclerViewOnGestureListener());

        this.show_details_rv.setAdapter(this.show_details_rv_adapter);
        this.show_details_rv.setLayoutManager(LLM);
        this.show_details_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            @Override
            public boolean onInterceptTouchEvent(@NonNull final RecyclerView recyclerView, @NonNull final MotionEvent motionEvent)
            {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    Observer<ArrayList<EventClass>> eventClassListUpdateObserver = new Observer<ArrayList<EventClass>>() {
        @Override
        public void onChanged(final ArrayList<EventClass> eventClasses) {
            Activity_Show_Details.this.show_details_rv_adapter.updateEventClasses();
        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        final Integer pos = this.getIntent().getIntExtra("pos",  0);
        final Event e = EventsViewModel.getModel().eventMutableLiveData.getValue().get(pos);
        this.eventClassRepository.FetchEventClassesFromEvent(e.getEventClasses());
        EventClassesViewModel.getModel().eventClasses.observe(this, this.eventClassListUpdateObserver);

        final TextView schedHead = this.findViewById(R.id.scheduleHeaderTV);
        final TextView zone = this.findViewById(R.id.zoneRegion);
        final TextView location = this.findViewById(R.id.showLocation);
        final TextView date = this.findViewById(R.id.showDateTime);

        schedHead.setText(e.getEventName());
        zone.setText(e.getZone() + "");
        location.setText(e.getLocation());
        date.setText(e.getEventTime().toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.eventClassRepository.unsubFirebase();
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(final MotionEvent e)
        {
            final View view = Activity_Show_Details.this.show_details_rv.findChildViewUnder(e.getX(), e.getY());
            if (view != null)
            {
                final RecyclerView.ViewHolder holder = Activity_Show_Details.this.show_details_rv.getChildViewHolder(view);

                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    Activity_Show_Details.this.openRider(holder.getAdapterPosition());
                    return true;
                }
            }
            return false;
        }
    }

    public void openRider(final int pos)
    {
        final Intent opSched = new Intent(this, Activity_Class_Order.class);
        opSched.putExtra("pos", pos);
        opSched.putIntegerArrayListExtra("riderIds", EventClassesViewModel.getModel().eventClasses
                .getValue().get(pos).getRiders());
        this.startActivity(opSched);
    }
}