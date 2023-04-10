package com.example.ihsastable.data.repository;

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
    // For the events arraylist, I may need to build a collection listener, Example: https://firebase.google.com/docs/firestore/query-data/listen#listen_to_multiple_documents_in_a_collection.
    // Then this will need to update the model being stored locally when this changes, and then we will need an observable to watch if data changes so it can update view.
    private Events events;
    public EventRepository(){
        EventRemoteTestDataSource ds = new EventRemoteTestDataSource();
        this.remoteCR = ds.getEventReference();
        this.events = Events.getModel();
        this.eventClassRepository = new EventClassRepository();
//        this.remoteCR.addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                events = getEventsAfterDate()????
//            }
//        });
    }
    public ArrayList<Event> getEventsAfterDate(Date date){
        this.remoteCR.whereGreaterThan("EventTime", date).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot doc : task.getResult()){
                    events.events.add(doc.toObject(Event.class));
                }
            }
        });
        return events.events;
    }

    public void createTestCollection(){

    }
}
