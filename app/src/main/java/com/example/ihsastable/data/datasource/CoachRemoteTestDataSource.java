package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CoachRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference coachesReference;
    public CoachRemoteTestDataSource(){
        db = FirebaseFirestore.getInstance();
        coachesReference = this.db.collection("Coach");
    }

    public CollectionReference getCoachesReference() {
        return this.coachesReference;
    }

}
