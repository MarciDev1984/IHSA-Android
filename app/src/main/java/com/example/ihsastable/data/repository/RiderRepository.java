package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.HorseRemoteTestDataSource;
import com.example.ihsastable.data.datasource.RiderRemoteTestDataSource;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Horse;
import com.example.ihsastable.data.model.Horses;
import com.example.ihsastable.data.model.Rider;
import com.example.ihsastable.data.model.Riders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RiderRepository {
    private CollectionReference remoteCR;
    public RiderRepository(){
        RiderRemoteTestDataSource ds = new RiderRemoteTestDataSource();
        this.remoteCR = ds.getRiderReference() ;
    }

    public void fetchRidersFromEventClass(EventClass ec) {
        ArrayList<Rider> riders = new ArrayList<>();
        this.remoteCR.whereIn("Id", ec.getRiders()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult()) {
                    riders.add(ds.toObject(Rider.class));
                }
                Riders.getModel().riders = riders;
            }
        });
    }
    public ArrayList<Rider> getRiders(){
        return Riders.getModel().riders;
    }
}
