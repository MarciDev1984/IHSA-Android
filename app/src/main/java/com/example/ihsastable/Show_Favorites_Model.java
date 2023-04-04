package com.example.ihsastable;

import java.util.ArrayList;

/*
 * This is Show_Favorites_Model
 *
 * Author: Fisher Reese
 */
public class Show_Favorites_Model
{
    public static class Favorites
    {
        private final String riderID, riderName;

        public Favorites(String riderID, String riderName) {this.riderID = riderID; this.riderName = riderName;}

        public String getFavorites() {return riderID;}
    }

    private ArrayList<Favorites> favoritesList;

    private Show_Favorites_Model()
    {
        favoritesList = new ArrayList<Favorites>();
        loadInitialTasks();
    }

    public void loadInitialTasks()
    {
    Favorites rider1 = new Favorites("001", "Fisher Reese");
    }

    public ArrayList<Show_Favorites_Model.Favorites> getTaskArray()
    {
        return favoritesList;
    }

    public static Show_Favorites_Model theModel = null;
    public static Show_Favorites_Model getSingleton()
    {
        if(theModel == null)
        {
            theModel = new Show_Favorites_Model();
        }
        return theModel;
    }

}
