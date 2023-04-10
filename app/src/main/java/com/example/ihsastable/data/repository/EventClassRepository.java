package com.example.ihsastable.data.repository;

import androidx.annotation.NonNull;

import com.example.ihsastable.data.datasource.EventClassRemoteTestDataSource;
import com.example.ihsastable.data.datasource.EventRemoteTestDataSource;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.model.Events;
import com.example.ihsastable.data.model.Horse;
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
        Task<QuerySnapshot> taskRef = this.remoteCR.whereEqualTo("id", id).get();
        final EventClass[] eventClass = new EventClass[1];
        Task<QuerySnapshot> querySnapshotTask = taskRef.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                eventClass[0] = task.getResult().getDocuments().get(0).toObject(EventClass.class);
            }
        });
        return eventClass[0];
    }
    public List<EventClass> getEventClassesFromEvent(Event e){
        List<EventClass> eventClasses = new ArrayList<>();
        for(int classId : e.getClasses()){
            eventClasses.add(getEventClassFromId(classId));
        }
        return eventClasses;
    }

}
