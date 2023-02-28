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
        scheduleList.add(new Schedule("Class 1"));
        scheduleList.add(new Schedule("Class 2"));
        scheduleList.add(new Schedule("Class 3"));
        scheduleList.add(new Schedule("Class 4"));
        scheduleList.add(new Schedule("Class 5"));
        scheduleList.add(new Schedule("Class 6"));
        scheduleList.add(new Schedule("Class 7"));
        scheduleList.add(new Schedule("Class 8"));
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
