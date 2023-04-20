package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.ihsastable.data.model.EventClass;

import java.util.ArrayList;

public class EventClassViewModel extends ViewModel
{
    public MutableLiveData<ArrayList<EventClass>> eventClassMutableLiveData = new MutableLiveData<>();
    private static EventClassViewModel theModel;

    public static EventClassViewModel getModel()
    {
        if (theModel == null) {theModel = new EventClassViewModel();}
        return theModel;
    }

    private EventClassViewModel() {this.eventClassMutableLiveData.setValue(new ArrayList<>());}
}