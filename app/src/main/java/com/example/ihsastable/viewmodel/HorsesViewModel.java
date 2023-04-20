package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.ihsastable.data.model.Horse;

import java.util.ArrayList;

public class HorsesViewModel {
    public MutableLiveData<ArrayList<Horse>> horses;
    private static HorsesViewModel theModel;
    public static HorsesViewModel getModel(){
        if (theModel == null){
            theModel = new HorsesViewModel();
        }
        return theModel;
    }
    private HorsesViewModel(){
        this.horses = new MutableLiveData<>();
    }
}
