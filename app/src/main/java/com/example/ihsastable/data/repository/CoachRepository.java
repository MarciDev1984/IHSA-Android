package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.CoachRemoteTestDataSource;
import com.example.ihsastable.data.datasource.EventRemoteTestDataSource;
import com.example.ihsastable.data.model.Coach;
import com.example.ihsastable.data.model.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

//Currently not being used
public class CoachRepository {
    private final CollectionReference remoteCR;
    private ArrayList<Coach> coaches;
    public CoachRepository(final CoachRemoteTestDataSource ds){
        remoteCR = ds.getCoachesReference();
    }
}
