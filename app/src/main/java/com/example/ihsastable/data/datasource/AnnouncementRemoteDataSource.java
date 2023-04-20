package com.example.ihsastable.data.datasource;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AnnouncementRemoteDataSource {
    private final FirebaseFirestore db;
    private final CollectionReference announcementReference;
    public AnnouncementRemoteDataSource(){
        db = FirebaseFirestore.getInstance();
        announcementReference = this.db.collection("Announcement");
    }

    public CollectionReference getAnnouncementReference() {
        return this.announcementReference;
    }
}
