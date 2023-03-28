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

public class AnnouncementRepository {
    CollectionReference remoteCR;
    private ArrayList<Announcement> Announcements;
    public AnnouncementRepository(AnnouncementRemoteDataSource remote){
        this.remoteCR = remote.getAnnouncementReference();
    }

    public ArrayList<Announcement> getAnnouncementsAfterDate(Timestamp timestamp){
        if (Announcements == null){
            Announcements = new ArrayList<>();
        }
        this.remoteCR.whereGreaterThan("TimePublished", timestamp).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {Announcements = new ArrayList<>();
                for(QueryDocumentSnapshot doc : task.getResult()){
                    Announcements.add(doc.toObject(Announcement.class));
                }
            }
        });
        return Announcements;
    }
}
