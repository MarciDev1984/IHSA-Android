package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.RiderRemoteTestDataSource;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Rider;
import com.example.ihsastable.viewmodel.EventClassesViewModel;
import com.example.ihsastable.viewmodel.RidersViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RiderRepository {
    private final CollectionReference remoteCR;
    ListenerRegistration listenerRegistration;
    public RiderRepository(){
        RiderRemoteTestDataSource ds = new RiderRemoteTestDataSource();
        this.remoteCR = ds.getRiderReference() ;
    }

    public void fetchRidersFromRiderIds(ArrayList<Integer> riderIds) {
        ArrayList<Rider> riders = new ArrayList<>();
        listenerRegistration = this.remoteCR.whereIn("Id", riderIds).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>()
        {
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
                        riders.add(doc.toObject(Rider.class));
                    }
                    RidersViewModel.getModel().riders.setValue(riders);
                }
            }
        });
    }

    public ArrayList<Rider> getRiders(){
        return RidersViewModel.getModel().riders.getValue();
    }
    public void unsubFirebase(){
        listenerRegistration.remove();
    }
}
