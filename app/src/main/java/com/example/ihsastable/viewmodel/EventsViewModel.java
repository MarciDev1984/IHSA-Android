package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ihsastable.data.model.Event;

import java.util.ArrayList;

public class EventsViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Event>> eventMutableLiveData = new MutableLiveData<>();
    private static EventsViewModel theModel;

    public static EventsViewModel getModel(){
        if (theModel == null){
            theModel = new EventsViewModel();
        }
        return theModel;
    }
    private EventsViewModel(){
        this.eventMutableLiveData.setValue(new ArrayList<>());}

//    public LiveData<String> eventName = Transformations.map(eventMutableLiveData, (String) ->{
//        return eventMutableLiveData.getValue().getEventName();
//    });
//    public LiveData<String> eventTime = Transformations.map(eventMutableLiveData, (String) ->{
//       return eventMutableLiveData.getValue().getEventTime().toString();
//    });
}
