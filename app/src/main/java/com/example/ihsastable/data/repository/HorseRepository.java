package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.EventClassRemoteTestDataSource;
import com.example.ihsastable.data.datasource.HorseRemoteTestDataSource;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Horse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HorseRepository {
    private CollectionReference remoteCR;
    public HorseRepository(){
        HorseRemoteTestDataSource ds = new HorseRemoteTestDataSource();
        this.remoteCR = ds.getHorseReference() ;
    }
    private Horse getHorseFromId(int id){
        Task<QuerySnapshot> taskRef = this.remoteCR.whereEqualTo("Id", id).get();
        final Horse[] horse = new Horse[1];
        Task<QuerySnapshot> querySnapshotTask = taskRef.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                horse[0] = task.getResult().getDocuments().get(0).toObject(Horse.class);
            }
        });
        return horse[0];
    }
    public ArrayList<Horse> getHorsesFromEventClass(EventClass ec){
        ArrayList<Horse> horses = new ArrayList<>();
        for(int horseId : ec.getHorses()){
            horses.add(getHorseFromId(horseId));
        }
        return horses;
    }

}
