package com.example.ihsastable.data.model;

import java.util.ArrayList;

public class SchoolSingleton {
    public School school;
    private static SchoolSingleton theModel;
    public static SchoolSingleton getModel(){
        if (SchoolSingleton.theModel == null){
            SchoolSingleton.theModel = new SchoolSingleton();
        }
        return SchoolSingleton.theModel;
    }
    private SchoolSingleton(){
        school = new School();
    }
}
