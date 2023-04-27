package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.AnnouncementRemoteDataSource;
import com.example.ihsastable.data.model.Announcement;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.viewmodel.AnnouncementModel;
import com.example.ihsastable.viewmodel.EventClassesViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

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
                    AnnouncementModel.getModel().announcementMutableLiveData.setValue(announcements);
                }
            }
        });
    }
    public ArrayList<Announcement> getAnnouncements(){
        return  AnnouncementModel.getModel().announcementMutableLiveData.getValue();
    }

    public void unsubFirebase(){
        listenerRegistration.remove();
    }

}
