package com.example.ihsastable.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.Events;

public class EventViewModel extends ViewModel {
    public MutableLiveData<Event> eventMutableLiveData = new MutableLiveData<>();
    public LiveData<String> eventName = Transformations.map(eventMutableLiveData, (String) ->{
        return eventMutableLiveData.getValue().getEventName();
    });
    public LiveData<String> eventTime = Transformations.map(eventMutableLiveData, (String) ->{
       return eventMutableLiveData.getValue().getEventTime().toString();
    });
}
