package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.EventRemoteTestDataSource;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.Events;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;

public class EventRepository
{
    private final CollectionReference remoteCR;
    private EventClassRepository eventClassRepository;
    public EventRepository()
    {
        EventRemoteTestDataSource eventRemoteTestDataSource = new EventRemoteTestDataSource();
        this.remoteCR = eventRemoteTestDataSource.getEventReference();
    }
    public void fetchEventsAfterOneYear()
    {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);

        ArrayList<Event> events = new ArrayList<>();

        Log.d("EventRepository", "fetching data using cal date: " + cal.getTime().toString());

        this.remoteCR.whereGreaterThan("EventTime", cal.getTime()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.getResult().isEmpty())
                {
                    Log.e("tests", "there should be stuff here");
                }
                else
                {
                    for (QueryDocumentSnapshot doc : task.getResult())
                    {
                        events.add(doc.toObject(Event.class));
                    }
                    Events.getModel().events = events;
                }
            }
        });
    }
    public ArrayList<Event> getEvents(){return Events.getModel().events;}
}
