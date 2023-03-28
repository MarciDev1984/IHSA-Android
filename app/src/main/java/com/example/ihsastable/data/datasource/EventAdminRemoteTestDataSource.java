package com.example.ihsastable.data.datasource;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class EventAdminRemoteTestDataSource {
    private FirebaseFirestore db;
    private CollectionReference eventAdminReference;
    public EventAdminRemoteTestDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.eventAdminReference = db.collection("EventAdmin");
    }

    public CollectionReference getEventAdminReference() {
        return eventAdminReference;
    }
}
