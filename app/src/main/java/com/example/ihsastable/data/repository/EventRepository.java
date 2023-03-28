package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.EventRemoteTestDataSource;
import com.example.ihsastable.data.model.Announcement;
import com.example.ihsastable.data.model.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class EventRepository {
    CollectionReference remoteCR;
    // For the events arraylist, I need to build a collection listener, Example: https://firebase.google.com/docs/firestore/query-data/listen#listen_to_multiple_documents_in_a_collection.
    // Then this will need to update the model being stored locally when this changes, and then we will need an observable to watch if data changes so it can update view.
    private ArrayList<Event> events;
    public EventRepository(EventRemoteTestDataSource ds){
        this.remoteCR = ds.getEventReference();
//        this.remoteCR.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//            }
//        });
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
    public void createTestCollection(){

    }
}
