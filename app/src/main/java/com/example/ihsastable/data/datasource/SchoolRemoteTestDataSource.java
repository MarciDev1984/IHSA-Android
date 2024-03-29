package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SchoolRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference schoolReference;
    public SchoolRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.schoolReference = db.collection("School");
    }

    public CollectionReference getSchoolReference() {
        return schoolReference;
    }
}
