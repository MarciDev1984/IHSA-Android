package com.example.ihsastable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.ihsastable.data.model.Announcement;
import com.example.ihsastable.data.repository.AnnouncementRepository;
import com.example.ihsastable.viewmodel.AnnouncementViewModel;

import java.util.ArrayList;

/*
 * This is Fragment_Announcement
 * This contains nothing at the moment
 * but will contain a RV for past announcements
 *
 * Author: Kooper Young, Christopher Burke
 */

public class Fragment_Announcement extends Fragment
{

    private View view;
    private RecyclerView announcement_rv;
    private RecyclerViewAdapter announcement_rv_adapter;

    private AnnouncementRepository announcementRepository;


    public Fragment_Announcement() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_announcement, container, false);
        announcement_rv = view.findViewById(R.id.announcement_rv);
        announcement_rv_adapter = new RecyclerViewAdapter("announcement_rv");

        LinearLayoutManager LLM = new LinearLayoutManager(view.getContext());
        GestureDetectorCompat gestureDetector = new GestureDetectorCompat(view.getContext(), new RecyclerViewOnGestureListener());

        announcement_rv.setAdapter(announcement_rv_adapter);
        announcement_rv.setLayoutManager(LLM);
        announcementRepository = new AnnouncementRepository();

        announcement_rv.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener()
        {
            //This is the first thing called on a tap, it passes it to the RecyclerViewOnGestureListener
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent)
            {
                Log.d("RV GESTURE", "Fragment_Announcement --- onCreateView -- onInterceptTouchEvent");
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

        return view;
    }
    Observer<ArrayList<Announcement>> announcementListUpdateObserver = new Observer<ArrayList<Announcement>>() {
        @Override
        public void onChanged(ArrayList<Announcement> announcements) {
            announcement_rv_adapter.updateAnnouncement();
        }
    };
    @Override
    public void onStart(){
        super.onStart();
        announcementRepository.FetchAnnouncement();
        AnnouncementViewModel.getModel().announcementMutableLiveData.observe(getViewLifecycleOwner(), announcementListUpdateObserver);


    }

    public void onStop(){
        super.onStop();
        announcementRepository.unsubFirebase();
    }
    private class RecyclerViewOnGestureListener extends GestureDetector.SimpleOnGestureListener
    {
        public boolean onSingleTapConfirmed(MotionEvent e)
        {
            Log.d("RV GESTURE", "Fragment_Announcement --- RecyclerViewOnGestureListener -- onSingleTapConfirmed");
            //Gets the coords of the tap and looks for a child at those coords
            View view = announcement_rv.findChildViewUnder(e.getX(), e.getY());

            //If there was a child
            if (view != null)
            {
                Log.d("RV GESTURE", "Fragment_Announcement --- RecyclerViewOnGestureListener -- onSingleTapConfirmed * child confirmed");
                RecyclerView.ViewHolder holder = announcement_rv.getChildViewHolder(view);

                //If the child was the right type
                if (holder instanceof RecyclerViewAdapter.RecyclerViewHolder)
                {
                    Log.d("RV GESTURE", "Fragment_Announcement --- RecyclerViewOnGestureListener -- " +
                            "onSingleTapConfirmed * child confirmed * correct type at position: " + holder.getAdapterPosition());
                    expandMessage(holder.getAdapterPosition());
                    return true;
                }
            }
            return false;
        }
    }

    public void expandMessage(int pos){
        Log.d("RV Clicked","You attempted to open an announcement");
    }
}