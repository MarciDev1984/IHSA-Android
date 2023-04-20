package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference userReference;
    public UserRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.userReference = db.collection("User");
    }

    public CollectionReference getUserReference() {
        return userReference;
    }
}
