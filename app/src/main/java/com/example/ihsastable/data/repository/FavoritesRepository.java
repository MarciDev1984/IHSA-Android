package com.example.ihsastable.data.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.ihsastable.data.datasource.FavoritesLocalDataSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FavoritesRepository {
    FavoritesLocalDataSource ds;
    SharedPreferences sp;

    public FavoritesRepository(Context context){
        ds = new FavoritesLocalDataSource(context);
        sp = ds.getSharedPreferences();
    }

    public void addToFavorites(int Id){
        SharedPreferences.Editor edit = ds.getSharedPreferences().edit();
        if(!ds.getSharedPreferences().contains("favorites")){
            edit.putStringSet("favorites", new HashSet<>(Id));
        }
        else{
            SharedPreferences sp = ds.getSharedPreferences();
            Set<String> hashSet = new HashSet<>(sp.getStringSet("favorites", new HashSet<>()));
            hashSet.add(String.valueOf(Id));
            edit.putStringSet("favorites", hashSet);
        }
        edit.commit();
    }
    public ArrayList<Integer> getFavoriteIds(){
        ArrayList<String> idArray = new ArrayList<>(sp.getStringSet("favorites", new HashSet<>()));
        ArrayList<Integer> ids = new ArrayList<>();
        for(String s : idArray){
            ids.add(Integer.parseInt(s));
        }
        return ids;
    }
    public void removeFromFavorites(int Id){
        SharedPreferences.Editor edit = ds.getSharedPreferences().edit();
        if(!ds.getSharedPreferences().contains("favorites")){
            edit.putStringSet("favorites", new HashSet<>());
        }

        SharedPreferences sp = ds.getSharedPreferences();
        Set<String> hashSet = new HashSet<>(sp.getStringSet("favorites", new HashSet<>()));
        if(hashSet.contains(String.valueOf(Id))) {
            hashSet.remove(String.valueOf(Id));
            edit.putStringSet("favorites", hashSet);
        }

        edit.commit();
    }
}
