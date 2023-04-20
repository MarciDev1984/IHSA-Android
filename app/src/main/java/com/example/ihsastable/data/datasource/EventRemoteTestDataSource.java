package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EventRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference eventReference;
    public EventRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.eventReference = db.collection("Event");
    }

    public CollectionReference getEventReference() {
        return eventReference;
    }
}
