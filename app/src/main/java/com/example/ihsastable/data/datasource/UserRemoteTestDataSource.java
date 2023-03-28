package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRemoteTestDataSource {
    private FirebaseFirestore db;
    private CollectionReference userReference;
    public UserRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.userReference = db.collection("Events");
    }

    public CollectionReference getUserReference() {
        return userReference;
    }
}
