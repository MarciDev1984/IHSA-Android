package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.ihsastable.data.model.EventClass;

import java.util.ArrayList;

public class EventClassesViewModel {
    public MutableLiveData<ArrayList<EventClass>> eventClasses = new MutableLiveData<>();
    private static EventClassesViewModel theModel;
    public static EventClassesViewModel getModel(){
        if (EventClassesViewModel.theModel == null){
            EventClassesViewModel.theModel = new EventClassesViewModel();
        }
        return EventClassesViewModel.theModel;
    }
    private EventClassesViewModel(){
        eventClasses.setValue(new ArrayList<>());
    }
}
