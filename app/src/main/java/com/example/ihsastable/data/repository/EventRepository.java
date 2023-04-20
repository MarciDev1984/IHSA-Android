package com.example.ihsastable.data.repository;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ihsastable.data.datasource.EventRemoteTestDataSource;
import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.viewmodel.EventsViewModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;

public class EventRepository
{
    private final CollectionReference remoteCR;
    private EventClassRepository eventClassRepository;
    ListenerRegistration listenerRegistration;
    public EventRepository()
    {
        final EventRemoteTestDataSource eventRemoteTestDataSource = new EventRemoteTestDataSource();
        remoteCR = eventRemoteTestDataSource.getEventReference();
    }
    public void fetchEventsAfterOneYear()
    {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);

        final ArrayList<Event> events = new ArrayList<>();
        Log.d("test", "fetching data using cal date: " + cal.getTime());

        this.listenerRegistration = remoteCR.whereGreaterThan("EventTime", cal.getTime()).limit(20).addSnapshotListener(new EventListener<QuerySnapshot>()
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
                        events.add(doc.toObject(Event.class));
                    }
                    EventsViewModel.getModel().eventMutableLiveData.setValue(events);
                }
            }
        });
    }
    public void unsubFirebase(){
        this.listenerRegistration.remove();
    }
    public ArrayList<Event> getEvents(){return EventsViewModel.getModel().eventMutableLiveData.getValue();}
}
