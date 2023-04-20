package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.EventClassRemoteTestDataSource;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.viewmodel.EventClassesViewModel;
import com.example.ihsastable.viewmodel.EventViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class EventClassRepository {
    private final CollectionReference remoteCR;
    private ListenerRegistration listenerRegistration;
    public EventClassRepository(){
        final EventClassRemoteTestDataSource ds = new EventClassRemoteTestDataSource();
        remoteCR = ds.getEventClassReference();
    }
    public void FetchEventClassesFromEvent(final ArrayList<Integer> classIds){
        final ArrayList<EventClass> eventClasses = new ArrayList<>();
        this.listenerRegistration = remoteCR.whereIn("Id", classIds).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>()
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
                        eventClasses.add(doc.toObject(EventClass.class));
                    }
                    EventClassesViewModel.getModel().eventClasses.setValue(eventClasses);
                }
            }
        });
    }
    public ArrayList<EventClass> getEventClasses(){
        return EventClassesViewModel.getModel().eventClasses.getValue();
    }
    public void unsubFirebase(){
        this.listenerRegistration.remove();
    }

}
