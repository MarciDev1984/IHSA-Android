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
 * Author: Kooper Young, Fisher Reese
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_favorite, container, false);

        this.favorites_rv = this.view.findViewById(R.id.favorites_rv);
        this.followBTN = this.view.findViewById(R.id.followBTN);
        this.followTV = this.view.findViewById(R.id.followET);

        // Get the root directory of your application's private storage
        final File rootDir = this.view.getContext().getFilesDir();

        // Create a file in the root directory
        this.file = new File(rootDir, "cache.txt");

        //Create a listener for follow button
        this.followBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final String riderInput = Fragment_Favorite.this.followTV.getText().toString();
                try {
                    Fragment_Favorite.this.favoriteValidation(riderInput);
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        final RecyclerViewAdapter favorites_rv_adapter = new RecyclerViewAdapter("favorites_rv");
        final LinearLayoutManager LLM = new LinearLayoutManager(this.view.getContext());
        final GestureDetectorCompat gestureDetector = new GestureDetectorCompat(this.view.getContext(), new RecyclerViewOnGestureListener());

        this.favorites_rv.setAdapter(favorites_rv_adapter);
        this.favorites_rv.setLayoutManager(LLM);
        this.favorites_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
            //This is the first thing called on a tap, it passes it to the RecyclerViewOnGestureListener
            @Override
            public boolean onInterceptTouchEvent(@NonNull final RecyclerView recyclerView, @NonNull final MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        this.followBTN.setEnabled(false);
        //Author: Jacob Pickman

        //Check if EditText meets pin requirements, if it is don't let the user press the button
        //Otherwise, RecyclerView will turn into a gift from the .
        this.followTV.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before,
                                      final int count) {
                // TODO Auto-generated method stub
                //Working Theory: Pin consists of three numbers
                //EditText only allows up to three characters so we don't care about max size
                Fragment_Favorite.this.followBTN.setEnabled(!s.toString().isEmpty() && s.toString().length() >= 3);
            }

            @Override //Don't Touch
            public void beforeTextChanged(final CharSequence s, final int start, final int count,
                                          final int after) {
            }

            @Override //Don't Touch
            public void afterTextChanged(final Editable s) {
            }
        });

        return this.view;
    }

    public void favoriteValidation(final String id) throws IOException {
        if (id.equals("001")) {
            this.userFavorites.put("001", "Fisher Reese");
            this.cachedata("001 Fisher Reese");
        } else if (id.equals("002")) {
            this.userFavorites.put("002", "Kooper Young");
        } else if (id.equals("003")) {
            this.userFavorites.put("003", "Gabriel Mura");
        } else if (id.equals("004")) {
            this.userFavorites.put("004", "Jacob Pickman");
        } else if (id.equals("005")) {
            this.userFavorites.put("005", "Kevin Harris");
        }
    }

    public void cachedata(final String riderName) throws IOException {
        // Write data to the file
        final FileOutputStream outputStream = new FileOutputStream(this.file);
        outputStream.write(riderName.getBytes());
        outputStream.close();

        // Read data from the file
        final FileInputStream inputStream = new FileInputStream(this.file);
        final byte[] buffer = new byte[1024];
        final int length = inputStream.read(buffer);
        final String data = new String(buffer, 0, length);
        inputStream.close();
    }

    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(final MotionEvent e)
        {
            final View view = Fragment_Favorite.this.favorites_rv.findChildViewUnder(e.getX(), e.getY());

            //If there was a child
            if (view != null)
            {
                final RecyclerView.ViewHolder holder = Fragment_Favorite.this.favorites_rv.getChildViewHolder(view);

                //If the child was the right type
                //TODO - Handle touch
                //openSchedule(holder.getAdapterPosition());
                return holder instanceof RecyclerViewAdapter.RecyclerViewHolder;
            }
            return false;
        }
    }
}