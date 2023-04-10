package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.HorseRemoteTestDataSource;
import com.example.ihsastable.data.datasource.SchoolRemoteTestDataSource;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Horse;
import com.example.ihsastable.data.model.School;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SchoolRepository {
    private CollectionReference remoteCR;
    public SchoolRepository(){
        SchoolRemoteTestDataSource ds = new SchoolRemoteTestDataSource();
        this.remoteCR = ds.getSchoolReference() ;
    }
    public School getSchoolFromId(int id){
        Task<QuerySnapshot> taskRef = this.remoteCR.whereEqualTo("id", id).get();
        final School[] school = new School[1];
        Task<QuerySnapshot> querySnapshotTask = taskRef.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                school[0] = task.getResult().getDocuments().get(0).toObject(School.class);
            }
        });
        return school[0];
    }

}
