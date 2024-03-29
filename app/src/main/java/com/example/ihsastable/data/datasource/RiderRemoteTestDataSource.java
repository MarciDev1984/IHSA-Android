package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RiderRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference riderReference;
    public RiderRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.riderReference = db.collection("Rider");
    }

    public CollectionReference getRiderReference() {
        return riderReference;
    }
}
