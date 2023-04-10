package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.HorseRemoteTestDataSource;
import com.example.ihsastable.data.datasource.RiderRemoteTestDataSource;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Horse;
import com.example.ihsastable.data.model.Rider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class RiderRepository {
    private CollectionReference remoteCR;
    public RiderRepository(){
        RiderRemoteTestDataSource ds = new RiderRemoteTestDataSource();
        this.remoteCR = ds.getRiderReference() ;
    }
    private Rider getRiderFromId(int id){
        DocumentReference docRef = this.remoteCR.document("id");
        final Rider[] rider = new Rider[1];
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                rider[0] = task.getResult().toObject(Rider.class);
            }
        });
        return rider[0];
    }
    public ArrayList<Rider> getRidersFromEventClass(EventClass ec){
        ArrayList<Rider> riders = new ArrayList<>();
        for(int riderId : ec.getRiders()){
            riders.add(getRiderFromId(riderId));
        }
        return riders;
    }
}
