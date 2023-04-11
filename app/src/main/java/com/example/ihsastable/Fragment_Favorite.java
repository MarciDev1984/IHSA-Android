package com.example.ihsastable;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 * This is Fragment_Favorite
 * This is where the riders you have chose to follow will be displayed
 *
 * Author: Kooper Young
 */

public class Fragment_Favorite extends Fragment {
    public Fragment_Favorite() {
    }

    private RecyclerView favorites_rv;
    private Button followBTN;
    private EditText followTV;
    private View view;
    private File file;

    //Array to store user Favorite riders
    Map<String, String> userFavorites = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //getActivity() or getContext() don't work in fragments.
        //This is the work-around
        //You can do view.getContext() if you need that
        view = inflater.inflate(R.layout.fragment_favorite, container, false);

        // Get the root directory of your application's private storage
        File rootDir = view.getContext().getFilesDir();

        // Create a file in the root directory
        file = new File(rootDir, "cache.txt");

        //Bind the RV object to the XML
        favorites_rv = view.findViewById(R.id.favorites_rv);
        followBTN = view.findViewById(R.id.followBTN);
        followTV = view.findViewById(R.id.followTV);

        //Create a new adapter instance with a key as an identifier
        RecyclerViewAdapter favorites_rv_adapter = new RecyclerViewAdapter("favorites_rv");

        //Create a LLM // was getActivity()
        LinearLayoutManager LLM = new LinearLayoutManager(view.getContext());

        //Create a listener for follow button
        followBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String riderInput = followTV.getText().toString();
                try {
                    favoriteValidation(riderInput);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        //Create a gestureDetector
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(view.getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(@NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(@NonNull MotionEvent e) {}

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
        favorites_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            //This is the first thing called on a tap, it passes it to the RecyclerViewOnGestureListener
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                Log.d("RV GESTURE", "Fragment_Home --- onCreateView -- onInterceptTouchEvent");
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        //Author: Jacob Pickman
        followBTN.setEnabled(false);
        //Author: Jacob Pickman

        //Check if EditText meets pin requirements, if it is don't let the user press the button
        //Otherwise, RecyclerView will turn into a gift from the Unabomber.
        followTV.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                //Working Theory: Pin consists of three numbers
                //EditText only allows up to three characters so we don't care about max size
                followBTN.setEnabled(!s.toString().isEmpty() && s.toString().length() >= 3);
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
}