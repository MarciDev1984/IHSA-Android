package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.AnnouncementRemoteDataSource;
import com.example.ihsastable.data.model.Announcement;
import com.example.ihsastable.viewmodel.AnnouncementViewModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

//Currently not being used
public class AnnouncementRepository {
    private final CollectionReference remoteCR;
    private ListenerRegistration listenerRegistration;

    public AnnouncementRepository(){
        AnnouncementRemoteDataSource announcementRemoteDataSource = new AnnouncementRemoteDataSource();
        this.remoteCR = announcementRemoteDataSource.getAnnouncementReference();
    }

    public void FetchAnnouncement(){
        ArrayList<Announcement> announcements = new ArrayList<>();
        listenerRegistration = this.remoteCR.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null)
                {
                    Log.e("tests", "listening to snapshot failed");
                }
                else
                {
                    for (QueryDocumentSnapshot doc : value)
                    {
                        announcements.add(doc.toObject(Announcement.class));
                    }
                    AnnouncementViewModel.getModel().announcementMutableLiveData.setValue(announcements);
                }
            }
        });
    }
    public ArrayList<Announcement> getAnnouncements(){
        return  AnnouncementViewModel.getModel().announcementMutableLiveData.getValue();
    }

    public void unsubFirebase(){
        listenerRegistration.remove();
    }

}
