package com.example.ihsastable.data.repository;

import com.example.ihsastable.data.datasource.AnnouncementRemoteDataSource;
import com.example.ihsastable.data.model.Announcement;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class AnnouncementRepository {
    CollectionReference remoteCR;
    private static ArrayList<Announcement> Announcements;
    public AnnouncementRepository(AnnouncementRemoteDataSource remote){
        this.remoteCR = remote.getAnnouncementReference();
    }

    public ArrayList<Announcement> getAnnouncements(){
//        if (Announcements == null){
//            Announcements = new ArrayList<>();
//        }
//        Query = this.remoteCR.whereGreaterThan("TimePublished", new Date());
//        this.remoteCR
    }
}
