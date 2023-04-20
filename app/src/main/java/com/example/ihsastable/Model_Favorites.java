package com.example.ihsastable;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
 * This is Show_Favorites_Model
 * Author: Fisher Reese
 */

public class Model_Favorites
{
    private File file;
    private String riderID, riderName;

    public static class Favorites
    {
        private final String riderID, riderName;

        public Favorites(String riderID, String riderName) {this.riderID = riderID; this.riderName = riderName;}

        public String getFavorites() {return riderID;}
    }

    private final ArrayList<Favorites> favoritesList;

    private Model_Favorites() throws IOException {
        favoritesList = new ArrayList<Favorites>();
        loadInitialTasks();
    }

    public void loadInitialTasks() throws IOException {
        if(file.exists())
        {
            Log.d("FILE", "File did exist");
        }
        else
        {
            Log.d("FILE", "File did not exist");
            file = new File(Environment.getExternalStorageDirectory(), "cache.txt");
        }


        Favorites rider1 = new Favorites("001", "Fisher Reese");
        favoritesList.add(rider1);

        // Write data to the file
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(riderName.getBytes());
        outputStream.close();

        // Read data from the file
        FileInputStream inputStream = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int length = inputStream.read(buffer);
        String data = new String(buffer, 0, length);
        inputStream.close();
    }

    public ArrayList<Model_Favorites.Favorites> getTaskArray()
    {
        return favoritesList;
    }

    public static Model_Favorites theModel;
    public static Model_Favorites getSingleton() throws IOException {
        if(theModel == null)
        {
            theModel = new Model_Favorites();
        }
        return theModel;
    }

}
