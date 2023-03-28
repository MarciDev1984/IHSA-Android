package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CoachRemoteTestDataSource {
    private FirebaseFirestore db;
    private CollectionReference coachesReference;
    public CoachRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.coachesReference = db.collection("Coaches");
    }

    public CollectionReference getCoachesReference() {
        return coachesReference;
    }

}
