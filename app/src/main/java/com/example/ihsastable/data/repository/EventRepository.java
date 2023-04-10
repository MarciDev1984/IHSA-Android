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
import java.util.Date;

public class EventRepository {
    private CollectionReference remoteCR;
    private EventClassRepository eventClassRepository;
    public EventRepository(){
        EventRemoteTestDataSource ds = new EventRemoteTestDataSource();
        this.remoteCR = ds.getEventReference();
    }
    public void fetchEventsAfterDate(Date date){
        ArrayList<Event> events = new ArrayList<>();
        this.remoteCR.whereGreaterThan("EventTime", date).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.getResult().isEmpty()){
                    Log.e("tests", "there should be stuff here");
                }
                else {
                    for (QueryDocumentSnapshot doc : task.getResult()) {
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
