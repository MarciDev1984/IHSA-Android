package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.ihsastable.data.model.Announcement;

import java.util.ArrayList;

public class AnnouncementViewModel {
    public MutableLiveData<ArrayList<Announcement>> announcementMutableLiveData = new MutableLiveData<>();
    private static AnnouncementViewModel theModel;

    public static AnnouncementViewModel getModel(){
        if (theModel == null){
            theModel = new AnnouncementViewModel();
        }
        return theModel;
    }

    private AnnouncementViewModel(){
        this.announcementMutableLiveData.setValue(new ArrayList<>());
    }

}
