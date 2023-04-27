package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.SchoolRemoteTestDataSource;
import com.example.ihsastable.data.model.School;
import com.example.ihsastable.data.model.SchoolSingleton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QuerySnapshot;

public class SchoolRepository {
    private final CollectionReference remoteCR;
    public SchoolRepository(){
        SchoolRemoteTestDataSource ds = new SchoolRemoteTestDataSource();
        this.remoteCR = ds.getSchoolReference() ;
    }
    public void fetchSchoolFromId(int id) {
        this.remoteCR.whereEqualTo("Id", id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                School school = task.getResult().getDocuments().get(0).toObject(School.class);
                SchoolSingleton.getModel().school = school;
            }
        });
    }
    public School getSchool(){
        return SchoolSingleton.getModel().school;
    }
}
