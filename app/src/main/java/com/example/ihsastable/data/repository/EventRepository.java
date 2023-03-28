package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.EventRemoteTestDataSource;
import com.example.ihsastable.data.model.Announcement;
import com.example.ihsastable.data.model.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class EventRepository {
    CollectionReference remoteCR;
    private ArrayList<Event> events;
    public EventRepository(EventRemoteTestDataSource ds){
        this.remoteCR = ds.getEventReference();
    }

    public ArrayList<Event> getEventsAfterDate(Date date){
        if (events == null){
            events = new ArrayList<>();
        }
        this.remoteCR.whereGreaterThan("EventTime", date).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot doc : task.getResult()){
                    events.add(doc.toObject(Event.class));
                }
            }
        });
        return events;
    }
}
