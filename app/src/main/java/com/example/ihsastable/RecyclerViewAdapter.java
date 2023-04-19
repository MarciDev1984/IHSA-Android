package com.example.ihsastable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihsastable.data.model.Event;
<<<<<<< Updated upstream
import com.example.ihsastable.data.model.Events;
=======
>>>>>>> Stashed changes
import com.example.ihsastable.data.repository.EventRepository;
import com.example.ihsastable.viewmodel.EventViewModel;

import java.util.ArrayList;

/*
 * This is RecyclerViewAdapter
 * This is a shared class between every instance of our RecyclerViews
 * Not the most elegant solution, but we are working on it
 * TODO - Work on a more elegant solution to this dumpster fire
 *
 * Author: Kooper Young
 */

/*
 * Keys per class
 * show_schedule_rv is for the RV in Fragment_Home
 * favorites_rv is for the RV in Fragment_Favorite
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>
{
<<<<<<< Updated upstream
    private String key;
    private Show_Schedule_Model show_schedule_model = Show_Schedule_Model.getSingleton();
    private EventRepository eventRepository = new EventRepository();
    private ArrayList<Event> events = new ArrayList<>();
    private classModel modelClass = classModel.getSingleton();
    private Rider_Schedule_Model modelRider = Rider_Schedule_Model.getSingleton();
=======
    private final String key;
    private final EventRepository eventRepository = new EventRepository();

>>>>>>> Stashed changes

    //TODO - Rework how this key/identification system works
    //This is called on a per-instance basis whenever you create a new RV and bind it to an adapter
    public RecyclerViewAdapter(String id)
    {
        super();
        key = id;
        Log.d("RV ADAPT", "RecyclerViewAdapter :: Key = " + key);
        if(key == "show_schedule_rv"){eventRepository.fetchEventsAfterOneYear();}

    }

    //TODO - See what happens when I try passing this different XML's
    @NonNull @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- onCreateViewHolder");

        RecyclerViewHolder RVH;
        View view;

        //For now, this is how we are going to pass different layout files in the adapter
        if(key.equals("show_schedule_rv"))
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell, parent, false);
            RVH = new RecyclerViewHolder(view);
            return RVH;
        }
        else
        {
            //TODO - Change this to return an error code and deal with it
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell, parent, false);
            RVH = new RecyclerViewHolder(view);
            return RVH;
        }
    }

    //So you pass this a view from onCreateViewHolder
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public RecyclerViewHolder(View v)
        {
            super(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position)
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- onBindViewHolder");
        TextView showTV = holder.itemView.findViewById(R.id.showTV);
        TextView dateTV = holder.itemView.findViewById(R.id.dateTV);
        EventViewModel eventViewModel = new EventViewModel();

        if(key.equals("show_schedule_rv"))
        {
<<<<<<< Updated upstream
            try {
                showTV.setText(eventRepository.getEvents().get(position).getEventName());
                dateTV.setText(eventRepository.getEvents().get(position).getEventTime().toString());
=======
            try
            {
                eventViewModel.eventMutableLiveData.setValue(eventRepository.getEvents().get(position));
                eventViewModel.eventMutableLiveData.observe(View, new Observer<Event>(){
                    @Override
                    public void onChanged(Event event) {
                        showTV.setText(event.getEventName());
                        dateTV.setText(event.getEventTime().toString());
                    }
                });

>>>>>>> Stashed changes
            }
            catch(Exception e){
                Log.d("test", eventRepository.getEvents().size() + "");
            }
        }
        else if(key.equals("rider_order_rv"))
        {
            //showTV.setText(modelClass.getSchedArray().get(position).getClassModel());
            dateTV.setText("");
        }
        else if(key.equals("idkyet")){
            //showTV.setText(modelRider.getOrderArray().get(position).getOrder());
            //dateTV.setText(modelRider.getOrderArray().get(position).getHorse());
        }
    }

    @Override
    public int getItemCount()
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- getItemCount");
        switch (key) {
            case "show_schedule_rv":
                return show_schedule_model.getTaskArray().size();
            case "rider_order_rv":
                //return modelClass.getSchedArray().size();
            case "idkyet":
                //return modelRider.getOrderArray().size();
            case "favorites_rv":
                return 0;
            default:
                //TODO - make this return something a little more useful. This will just crash
                return 0;
        }
    }
}
