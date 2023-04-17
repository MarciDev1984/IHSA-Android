package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class HorseRemoteTestDataSource {
    private FirebaseFirestore db;
    private CollectionReference horseReference;
    public HorseRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.horseReference = db.collection("Horse");
    }

    public CollectionReference getHorseReference() {
        return horseReference;
    }
}
