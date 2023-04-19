package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.EventClassRemoteTestDataSource;
import com.example.ihsastable.data.datasource.EventRemoteTestDataSource;
import com.example.ihsastable.data.model.Announcement;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Events;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class EventRepository {
    private CollectionReference remoteCR;
    private EventClassRepository eventClassRepository;
    public EventRepository(){
        EventRemoteTestDataSource ds = new EventRemoteTestDataSource();
        this.remoteCR = ds.getEventReference();
    }
    public void fetchEventsAfterOneYear(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        ArrayList<Event> events = new ArrayList<>();
        Log.d("EventRepository", "fetching data using cal date: " + cal.getTime().toString());

        this.remoteCR.whereGreaterThan("EventTime", cal.getTime()).addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null)
                {
                    Log.e("tests", "listening to snapshot failed");
                }
                else
                {
                    for (QueryDocumentSnapshot doc : value)
                    {
                        events.add(doc.toObject(Event.class));
                    }
                    Events.getModel().events = events;
                }
            }
        });
    }
    public ArrayList<Event> getEvents(){
        return Events.getModel().events;
    }
}
