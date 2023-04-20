package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.ihsastable.data.model.EventClass;

import java.util.ArrayList;

public class EventClassesViewModel {
    public MutableLiveData<ArrayList<EventClass>> eventClasses = new MutableLiveData<>();
    private static EventClassesViewModel theModel;
    public static EventClassesViewModel getModel(){
        if (theModel == null){
            theModel = new EventClassesViewModel();
        }
        return theModel;
    }
    private EventClassesViewModel(){
        this.eventClasses.setValue(new ArrayList<>());
    }
}
