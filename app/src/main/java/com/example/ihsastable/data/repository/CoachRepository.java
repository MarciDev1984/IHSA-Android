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

public class CoachRepository {
    private CollectionReference remoteCR;
    private ArrayList<Coach> coaches;
    public CoachRepository(CoachRemoteTestDataSource ds){
        this.remoteCR = ds.getCoachesReference();
    }

    public ArrayList<Coach> getCoaches(){
        if (coaches == null){
            coaches = new ArrayList<>();
        }
        this.remoteCR.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot doc : task.getResult()){
                    coaches.add(doc.toObject(Coach.class));
                }
            }
        });
        return coaches;
    }
}
