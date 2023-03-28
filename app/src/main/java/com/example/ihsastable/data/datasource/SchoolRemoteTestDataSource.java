package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SchoolRemoteTestDataSource {
    private FirebaseFirestore db;
    private CollectionReference schoolReference;
    public SchoolRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.schoolReference = db.collection("Events");
    }

    public CollectionReference getSchoolReference() {
        return schoolReference;
    }
}
