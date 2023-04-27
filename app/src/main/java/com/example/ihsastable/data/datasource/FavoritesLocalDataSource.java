package com.example.ihsastable.data.datasource;

import android.content.Context;
import android.content.SharedPreferences;

public class FavoritesLocalDataSource {
    private SharedPreferences sp;

    public FavoritesLocalDataSource(Context context){
        sp = context.getSharedPreferences("favorites", Context.MODE_PRIVATE);
    }
    public SharedPreferences getSharedPreferences(){
        return sp;
    }
}
