package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ihsastable.data.model.Rider;

import java.util.ArrayList;

public class RidersViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Rider>> riders;
    private static RidersViewModel theModel;
    public static RidersViewModel getModel(){
        if (theModel == null){
            theModel = new RidersViewModel();
        }
        return theModel;
    }
    private RidersViewModel(){
        this.riders = new MutableLiveData<>();
    }
}
