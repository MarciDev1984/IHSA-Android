package com.example.ihsastable;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihsastable.data.model.Rider;
import com.example.ihsastable.data.repository.RiderRepository;
import com.example.ihsastable.viewmodel.EventsViewModel;
import com.example.ihsastable.viewmodel.RidersViewModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * This is Fragment_Favorite
 * This is where the riders you have chose to follow will be displayed
 *
 * Author: Kooper Young, Fisher Reese
 */

public class Fragment_Favorite extends Fragment
{
    public Fragment_Favorite() {}

    private RecyclerView favorites_rv;
    private Button followBTN;
    private EditText followTV;
    private View view;
    private File file;
    private RiderRepository riderRepository;
    private RecyclerViewAdapter favorites_rv_adapter;

    //Array to store user Favorite riders
    Map<String, String> userFavorites = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorite, container, false);

        favorites_rv = view.findViewById(R.id.favorites_rv);
        followBTN = view.findViewById(R.id.followBTN);
        followTV = view.findViewById(R.id.followET);

        // Get the root directory of your application's private storage
        File rootDir = view.getContext().getFilesDir();

        // Create a file in the root directory
        file = new File(rootDir, "cache.txt");


        favorites_rv_adapter = new RecyclerViewAdapter("favorites_rv");
        LinearLayoutManager LLM = new LinearLayoutManager(view.getContext());
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(view.getContext(), new RecyclerViewOnGestureListener());

        riderRepository = new RiderRepository();
        favorites_rv.setAdapter(favorites_rv_adapter);
        favorites_rv.setLayoutManager(LLM);
        favorites_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            //This is the first thing called on a tap, it passes it to the RecyclerViewOnGestureListener
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        //Author: Jacob Pickman
        //Create a listener for follow button
        followBTN.setEnabled(false);

        followBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String riderInput = followTV.getText().toString();
                try
                {
                    favoriteValidation(riderInput);
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        //Check if EditText meets pin requirements, if it is don't let the user press the button
        //Otherwise, RecyclerView will turn into a gift from the .
        followTV.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                // TODO Auto-generated method stub
                //Working Theory: Pin consists of three numbers
                //EditText only allows up to three characters so we don't care about max size
                followBTN.setEnabled(!s.toString().isEmpty() && s.toString().length() >= 3);
            }

            @Override //Don't Touch
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override //Don't Touch
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    Observer<ArrayList<Rider>> riderListUpdateObserver = new Observer<ArrayList<Rider>>() {
        @Override
        public void onChanged(ArrayList<Rider> riders) {favorites_rv_adapter.updateEvents();}
    };

    @Override
    public void onStart() {
        super.onStart();
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ids.add(111);
        ids.add(222);
        ids.add(333);
        ids.add(444);
        riderRepository.fetchRidersFromRiderIds(ids);
        RidersViewModel.getModel().riders.observe(getViewLifecycleOwner(), riderListUpdateObserver);
    }
    public void onStop(){
        super.onStop();
        riderRepository.unsubFirebase();
    }

    public void favoriteValidation(String id) throws IOException {
        if (id.equals("001")) {
            userFavorites.put("001", "Fisher Reese");
            cachedata("001 Fisher Reese");
        } else if (id.equals("002")) {
            userFavorites.put("002", "Kooper Young");
        } else if (id.equals("003")) {
            userFavorites.put("003", "Gabriel Mura");
        } else if (id.equals("004")) {
            userFavorites.put("004", "Jacob Pickman");
        } else if (id.equals("005")) {
            userFavorites.put("005", "Kevin Harris");
        }
    }

    public void cachedata(String riderName) throws IOException {
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

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            View view = favorites_rv.findChildViewUnder(e.getX(), e.getY());

            //If there was a child
            if (view != null)
            {
                RecyclerView.ViewHolder holder = favorites_rv.getChildViewHolder(view);

                //If the child was the right type
                //TODO - Handle touch
                //openSchedule(holder.getAdapterPosition());
                return holder instanceof RecyclerViewAdapter.RecyclerViewHolder;
            }
            return false;
        }
    }
}