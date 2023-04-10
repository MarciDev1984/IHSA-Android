package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.EventClassRemoteTestDataSource;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.EventClasses;
import com.example.ihsastable.data.model.Rider;
import com.example.ihsastable.data.model.Riders;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EventClassRepository {
    private CollectionReference remoteCR;
    public EventClassRepository(){
        EventClassRemoteTestDataSource ds = new EventClassRemoteTestDataSource();
        this.remoteCR = ds.getEventClassReference();
    }
    public void FetchEventClassesFromEvent(Event e){
        ArrayList<EventClass> eventClasses = new ArrayList<>();
        this.remoteCR.whereIn("Id", e.getEventClasses()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (DocumentSnapshot ds : task.getResult()) {
                    eventClasses.add(ds.toObject(EventClass.class));
                }
                EventClasses.getModel().eventClasses = eventClasses;
            }
        });
    }
    public ArrayList<EventClass> getEventClasses(){
        return EventClasses.getModel().eventClasses;
    }

}
