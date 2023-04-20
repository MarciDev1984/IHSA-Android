package com.example.ihsastable.data.model;

import java.util.ArrayList;

public class SchoolSingleton {
    public School school;
    private static SchoolSingleton theModel;
    public static SchoolSingleton getModel(){
        if (theModel == null){
            theModel = new SchoolSingleton();
        }
        return theModel;
    }
    private SchoolSingleton(){
        this.school = new School();
    }
}
