package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AnnouncementRemoteDataSource {
    private FirebaseFirestore db;
    private CollectionReference announcementReference;
    public AnnouncementRemoteDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.announcementReference = db.collection("Announcements");
    }

    public CollectionReference getAnnouncementReference() {
        return announcementReference;
    }
}
