package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.AnnouncementRemoteDataSource;
import com.example.ihsastable.data.model.Announcement;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

//Currently not being used
public class AnnouncementRepository {
    private final CollectionReference remoteCR;
    private ArrayList<Announcement> Announcements;
    public AnnouncementRepository(AnnouncementRemoteDataSource remote){
        this.remoteCR = remote.getAnnouncementReference();
    }
}
