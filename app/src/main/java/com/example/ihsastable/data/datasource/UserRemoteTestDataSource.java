package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference userReference;
    public UserRemoteTestDataSource(){
        db = FirebaseFirestore.getInstance();
        userReference = this.db.collection("User");
    }

    public CollectionReference getUserReference() {
        return this.userReference;
    }
}
