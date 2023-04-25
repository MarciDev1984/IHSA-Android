package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AnnouncementRemoteDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference announcementReference;
    public AnnouncementRemoteDataSource(){
        this.db = FirebaseFirestore.getInstance();
        this.announcementReference = db.collection("Announcement");
    }

    public CollectionReference getAnnouncementReference() {
        return announcementReference;
    }
}
