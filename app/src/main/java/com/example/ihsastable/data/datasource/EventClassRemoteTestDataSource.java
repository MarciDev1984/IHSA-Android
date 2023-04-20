package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EventClassRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference eventClassReference;
    public EventClassRemoteTestDataSource(){
        db = FirebaseFirestore.getInstance();
        eventClassReference = this.db.collection("EventClass");
    }

    public CollectionReference getEventClassReference() {
        return this.eventClassReference;
    }
}
