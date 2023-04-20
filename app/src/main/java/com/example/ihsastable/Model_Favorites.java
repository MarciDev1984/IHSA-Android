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

        public Favorites(final String riderID, final String riderName) {this.riderID = riderID; this.riderName = riderName;}

        public String getFavorites() {return this.riderID;}
    }

    private final ArrayList<Favorites> favoritesList;

    private Model_Favorites() throws IOException {
        this.favoritesList = new ArrayList<Favorites>();
        this.loadInitialTasks();
    }

    public void loadInitialTasks() throws IOException {
        if(this.file.exists())
        {
            Log.d("FILE", "File did exist");
        }
        else
        {
            Log.d("FILE", "File did not exist");
            this.file = new File(Environment.getExternalStorageDirectory(), "cache.txt");
        }


        final Favorites rider1 = new Favorites("001", "Fisher Reese");
        this.favoritesList.add(rider1);

        // Write data to the file
        final FileOutputStream outputStream = new FileOutputStream(this.file);
        outputStream.write(this.riderName.getBytes());
        outputStream.close();

        // Read data from the file
        final FileInputStream inputStream = new FileInputStream(this.file);
        final byte[] buffer = new byte[1024];
        final int length = inputStream.read(buffer);
        final String data = new String(buffer, 0, length);
        inputStream.close();
    }

    public ArrayList<Model_Favorites.Favorites> getTaskArray()
    {
        return this.favoritesList;
    }

    public static Model_Favorites theModel;
    public static Model_Favorites getSingleton() throws IOException {
        if(Model_Favorites.theModel == null)
        {
            Model_Favorites.theModel = new Model_Favorites();
        }
        return Model_Favorites.theModel;
    }

}
