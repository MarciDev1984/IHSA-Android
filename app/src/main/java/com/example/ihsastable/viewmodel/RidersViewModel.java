package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ihsastable.data.model.Rider;

import java.util.ArrayList;

public class RidersViewModel extends ViewModel {

    public MutableLiveData<ArrayList<Rider>> riders;
    private static RidersViewModel theModel;
    public static RidersViewModel getModel(){
        if (RidersViewModel.theModel == null){
            RidersViewModel.theModel = new RidersViewModel();
        }
        return RidersViewModel.theModel;
    }
    private RidersViewModel(){
        riders = new MutableLiveData<>();
    }
}
