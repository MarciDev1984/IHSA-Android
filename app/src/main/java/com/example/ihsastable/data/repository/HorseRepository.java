package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.HorseRemoteTestDataSource;
import com.example.ihsastable.data.model.Horse;
import com.example.ihsastable.viewmodel.HorsesViewModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HorseRepository {
    private final CollectionReference remoteCR;
    ListenerRegistration listenerRegistration;
    public HorseRepository(){
        final HorseRemoteTestDataSource ds = new HorseRemoteTestDataSource();
        remoteCR = ds.getHorseReference() ;
    }

    public void fetchHorsesFromHorseIds(final ArrayList horseIds){
        final ArrayList<Horse> horses = new ArrayList<>();
        // getHorses returns a list of Horse id's
        this.listenerRegistration = remoteCR.whereIn("Id", horseIds).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(@Nullable final QuerySnapshot value, @Nullable final FirebaseFirestoreException error) {
                if(error != null)
                {
                    Log.e("tests", "listening to snapshot failed");
                }
                else
                {
                    for (final QueryDocumentSnapshot doc : value)
                    {
                        horses.add(doc.toObject(Horse.class));
                    }
                    HorsesViewModel.getModel().horses.setValue(horses);
                }
            }
        });
    }
    public ArrayList<Horse> getHorses(){
        return HorsesViewModel.getModel().horses.getValue();
    }
    public void unsubFirebase(){
        this.listenerRegistration.remove();
    }
}
//for testing
//                for(Horse h : HorsesViewModel.getModel().horses){
//                    Log.e("horse", h.getId() +
//                            h.getDescription() +
//                            h.getName() +
//                            h.getProvider());
//                }

