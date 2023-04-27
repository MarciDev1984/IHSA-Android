package com.example.ihsastable.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.ihsastable.data.model.Announcement;

import java.util.ArrayList;

public class AnnouncementModel {
    public MutableLiveData<ArrayList<Announcement>> announcementMutableLiveData = new MutableLiveData<>();
    private static AnnouncementModel theModel;

    public static AnnouncementModel getModel(){
        if (theModel == null){
            theModel = new AnnouncementModel();
        }
        return theModel;
    }

    private AnnouncementModel(){
        this.announcementMutableLiveData.setValue(new ArrayList<>());
    }

}
