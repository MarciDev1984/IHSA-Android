package com.example.ihsastable;

import java.util.ArrayList;

public class classModel {

    private ArrayList<Schedule> scheduleList;

    public static class Schedule{
        private String thisClass;

        public Schedule(String someClass){
            this.thisClass = someClass;
        }
        public String getClassModel(){
            return thisClass;
        }
    }

    private classModel(){
        scheduleList = new ArrayList<Schedule>();
        loadSched();
    }

    public void loadSched(){
        scheduleList.add(new Schedule("Show Class 1 Rider Class 8"));
        scheduleList.add(new Schedule("Show Class 2 Rider Class 6"));
        scheduleList.add(new Schedule("Show Class 3 Rider Class 4"));
        scheduleList.add(new Schedule("Show Class 4 Rider Class 4"));
        scheduleList.add(new Schedule("Show Class 5 Rider Class 7"));
        scheduleList.add(new Schedule("Show Class 6 Rider Class 5"));
        scheduleList.add(new Schedule("Show Class 7 Rider Class 3"));
        scheduleList.add(new Schedule("Show Class 8 Rider Class 2B"));
    }

    public ArrayList<Schedule> getSchedArray(){
        return scheduleList;
    }

    public static classModel theModel = null;

    public static classModel getSingleton(){
        if(theModel == null){
            theModel = new classModel();
        }
        return theModel;
    }
}
