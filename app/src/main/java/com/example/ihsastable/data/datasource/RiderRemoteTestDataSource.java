package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RiderRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference riderReference;
    public RiderRemoteTestDataSource(){
        db = FirebaseFirestore.getInstance();
        riderReference = this.db.collection("Rider");
    }

    public CollectionReference getRiderReference() {
        return this.riderReference;
    }
}
