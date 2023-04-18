package com.example.ihsastable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.Events;
import com.example.ihsastable.data.repository.EventRepository;

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
 *
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>
{
    private String key;
    private Show_Schedule_Model show_schedule_model = Show_Schedule_Model.getSingleton();
    private EventRepository eventRepository = new EventRepository();
    private ArrayList<Event> events = new ArrayList<>();
    private classModel modelClass = classModel.getSingleton();
    private Rider_Schedule_Model modelRider = Rider_Schedule_Model.getSingleton();

    //TODO - Rework how this key/identification system works
    //This is called on a per-instance basis whenever you create a new RV and bind it to an adapter
    public RecyclerViewAdapter(String id)
    {
        super();
        key = id;
        Log.d("RV ADAPT", "RecyclerViewAdapter :: Key = " + key);
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
        eventRepository.fetchEventsAfterOneYear();

        if(key.equals("show_schedule_rv"))
        {
            showTV.setText(eventRepository.getEvents().get(position).getEventName());
            dateTV.setText(eventRepository.getEvents().get(position).getEventTime().toString());
        }
        else if(key.equals("rider_order_rv"))
        {
            showTV.setText(modelClass.getSchedArray().get(position).getClassModel());
            dateTV.setText("");
        }
        else if(key.equals("idkyet")){
            showTV.setText(modelRider.getOrderArray().get(position).getOrder());
            dateTV.setText(modelRider.getOrderArray().get(position).getHorse());
        }
    }

    @Override
    public int getItemCount()
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- getItemCount");
        if(key.equals("show_schedule_rv"))
        {
            return show_schedule_model.getTaskArray().size();
        }
        else if(key.equals("rider_order_rv"))
        {
            return modelClass.getSchedArray().size();
        }
        else if (key.equals("idkyet"))
        {
            return modelRider.getOrderArray().size();
        }
        else
        {
            //TODO - make this return something a little more useful. This will just crash
            return 0;
        }
    }
}
