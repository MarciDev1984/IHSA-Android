package com.example.ihsastable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

/*
 * This is Fragment_Favorite
 * This is where the riders you have chose to follow will be displayed
 *
 * Author: Kooper Young
 */

public class Fragment_Favorite extends Fragment
{
    public Fragment_Favorite() {}
    private RecyclerView favorites_rv;
    private View view;

    HashMap<String, String> userFavorites = new HashMap<String, String>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //getActivity() or getContext() don't work in fragments.
        //This is the work-around
        //You can do view.getContext() if you need that
        view = inflater.inflate(R.layout.fragment_favorite, container, false);

        //Bind the RV object to the XML
        favorites_rv = view.findViewById(R.id.favorites_rv);

        //Create a new adapter instance with a key as an identifier
        RecyclerViewAdapter favorites_rv_adapter = new RecyclerViewAdapter("favorites_rv");

        //Create a LLM // was getActivity()
        LinearLayoutManager LLM = new LinearLayoutManager(view.getContext());

        //Create a gestureDetector
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(view.getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(@NonNull MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(@NonNull MotionEvent e) {

            }

            @Override
            public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        //Set the adapter, LLM, and gestureDetector
        favorites_rv.setAdapter(favorites_rv_adapter);
        favorites_rv.setLayoutManager(LLM);
        favorites_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            //This is the first thing called on a tap, it passes it to the RecyclerViewOnGestureListener
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                Log.d("RV GESTURE", "Fragment_Home --- onCreateView -- onInterceptTouchEvent");
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        //Author: Jacob Pickman
        TextView followTV = view.findViewById(R.id.followTV);
        Button followBTN = view.findViewById(R.id.followBTN);
        followBTN.setEnabled(false);
        //Author: Jacob Pickman
        //Make the view actually load properly
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        LinearLayoutManager LLM = new LinearLayoutManager(view.getContext());

        TextView followTV = view.findViewById(R.id.followTV);
        Button followBTN = view.findViewById(R.id.followBTN);
        followBTN.setEnabled(false);
        RecyclerView followRV = view.findViewById(R.id.followRV);

        //Check if EditText meets pin requirements, if it is don't let the user press the button
        //Otherwise, RecyclerView will turn into a gift from the Unabomber.
        followTV.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                //Working Theory: Pin consists of three numbers
                //EditText only allows up to three characters so we don't care about max size
                if (s.toString().isEmpty() || s.toString().length() < 3) {
                    followBTN.setEnabled(false);
                } else {
                    followBTN.setEnabled(true);
                }
            }

            @Override //Don't Touch
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override //Don't Touch
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    public void favoriteValidation(String id){
        if(id.equals("001")){
            userFavorites.put("001", "Fisher Reese");
        }
        else if(id.equals("002")){
            userFavorites.put("002", "Kooper Young");
        }
        else if(id.equals("003")){
            userFavorites.put("003", "Gabriel Mura");
        }
        else if(id.equals("004")){
            userFavorites.put("004", "Jacob Pickman");
        }
        else if(id.equals("005")){
            userFavorites.put("005", "Kevin Harris");
        }
    }

    public void favoriteDelete(String id){
        userFavorites.remove(id);
    }
}