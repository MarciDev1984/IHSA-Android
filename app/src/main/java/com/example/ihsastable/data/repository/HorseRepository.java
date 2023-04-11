package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.EventClassRemoteTestDataSource;
import com.example.ihsastable.data.datasource.HorseRemoteTestDataSource;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Horse;
import com.example.ihsastable.data.model.Horses;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HorseRepository {
    private CollectionReference remoteCR;
    public HorseRepository(){
        HorseRemoteTestDataSource ds = new HorseRemoteTestDataSource();
        this.remoteCR = ds.getHorseReference() ;
    }

    public void fetchHorsesFromEventClass(EventClass ec){
        ArrayList<Horse> horses = new ArrayList<>();
        this.remoteCR.whereIn("Id", ec.getHorses()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(DocumentSnapshot ds : task.getResult()){
                    horses.add(ds.toObject(Horse.class));
                }
                Horses.getModel().horses = horses;
            }
        });
    }
    public ArrayList<Horse> getHorses(){
        return Horses.getModel().horses;
    }

}
//for testing
//                for(Horse h : Horses.getModel().horses){
//                    Log.e("horse", h.getId() +
//                            h.getDescription() +
//                            h.getName() +
//                            h.getProvider());
//                }

