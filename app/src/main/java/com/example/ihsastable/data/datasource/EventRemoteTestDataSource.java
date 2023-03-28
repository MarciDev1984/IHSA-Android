package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EventRemoteTestDataSource {
    private FirebaseFirestore db;
    private CollectionReference eventReference;
    public EventRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.eventReference = db.collection("Events");
    }

    public CollectionReference getEventReference() {
        return eventReference;
    }
}
