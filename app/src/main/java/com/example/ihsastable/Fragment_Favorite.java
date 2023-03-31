package com.example.ihsastable;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/*
 * This is Fragment_Favorite
 * This is where the riders you have chose to follow will be displayed
 *
 * Author: Kooper Young
 */

public class Fragment_Favorite extends Fragment
{
    public Fragment_Favorite() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
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
}