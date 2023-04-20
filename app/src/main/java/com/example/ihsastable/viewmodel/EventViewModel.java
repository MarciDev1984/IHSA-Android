package com.example.ihsastable.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.Events;
import com.example.ihsastable.data.repository.EventRepository;

import java.util.ArrayList;

public class EventViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Event>> eventMutableLiveData = new MutableLiveData<>();
    private static EventViewModel theModel;

    public static EventViewModel getModel(){
        if (theModel == null){
            theModel = new EventViewModel();
        }
        return theModel;
    }
    private EventViewModel(){this.eventMutableLiveData.setValue(new ArrayList<>());}

//    public LiveData<String> eventName = Transformations.map(eventMutableLiveData, (String) ->{
//        return eventMutableLiveData.getValue().getEventName();
//    });
//    public LiveData<String> eventTime = Transformations.map(eventMutableLiveData, (String) ->{
//       return eventMutableLiveData.getValue().getEventTime().toString();
//    });
}
