package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EventClassRemoteTestDataSource {
    private FirebaseFirestore db;
    private CollectionReference eventClassReference;
    public EventClassRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.eventClassReference = db.collection("EventClass");
    }

    public CollectionReference getEventClassReference() {
        return eventClassReference;
    }
}
