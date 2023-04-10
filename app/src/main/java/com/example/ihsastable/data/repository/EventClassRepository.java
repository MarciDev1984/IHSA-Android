package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.EventClassRemoteTestDataSource;
import com.example.ihsastable.data.datasource.EventRemoteTestDataSource;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Events;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventClassRepository {
    private CollectionReference remoteCR;
    public EventClassRepository(){
        EventClassRemoteTestDataSource ds = new EventClassRemoteTestDataSource();
        this.remoteCR = ds.getEventClassReference();
    }
    private EventClass getEventClassFromId(int id){
        DocumentReference docRef = this.remoteCR.document("id");
        final EventClass[] event = new EventClass[1];
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                event[0] = task.getResult().toObject(EventClass.class);
            }
        });
        return event[0];
    }
    public List<EventClass> getEventClassesFromEvent(Event e){
        List<EventClass> eventClasses = new ArrayList<>();
        for(int classId : e.getClasses()){
            eventClasses.add(getEventClassFromId(classId));
        }
        return eventClasses;
    }

}
