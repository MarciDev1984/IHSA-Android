package com.example.ihsastable;

import java.util.ArrayList;

public class Model_Show_Class {

    private final ArrayList<Schedule> scheduleList;

    public static class Schedule{
        private final String thisClass;

        public Schedule(final String someClass){
            thisClass = someClass;
        }
        public String getClassModel(){
            return this.thisClass;
        }
    }

    private Model_Show_Class(){
        this.scheduleList = new ArrayList<Schedule>();
        this.loadSched();
    }

    public void loadSched(){
        this.scheduleList.add(new Schedule("Show Class 1 Rider Class 8"));
        this.scheduleList.add(new Schedule("Show Class 2 Rider Class 6"));
        this.scheduleList.add(new Schedule("Show Class 3 Rider Class 4"));
        this.scheduleList.add(new Schedule("Show Class 4 Rider Class 4"));
        this.scheduleList.add(new Schedule("Show Class 5 Rider Class 7"));
        this.scheduleList.add(new Schedule("Show Class 6 Rider Class 5"));
        this.scheduleList.add(new Schedule("Show Class 7 Rider Class 3"));
        this.scheduleList.add(new Schedule("Show Class 8 Rider Class 2B"));
    }

    public ArrayList<Schedule> getSchedArray(){
        return this.scheduleList;
    }

    public static Model_Show_Class theModel;

    public static Model_Show_Class getSingleton(){
        if(Model_Show_Class.theModel == null){
            Model_Show_Class.theModel = new Model_Show_Class();
        }
        return Model_Show_Class.theModel;
    }
}
