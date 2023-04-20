package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class HorseRemoteTestDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference horseReference;
    public HorseRemoteTestDataSource(){
        db = FirebaseFirestore.getInstance();
        horseReference = this.db.collection("Horse");
    }

    public CollectionReference getHorseReference() {
        return this.horseReference;
    }
}
