package com.example.ihsastable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihsastable.data.model.Event;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.repository.EventClassRepository;
import com.example.ihsastable.data.repository.EventRepository;
import com.example.ihsastable.viewmodel.EventViewModel;

import java.util.ArrayList;

/*
 * This is RecyclerViewAdapter
 * This is a shared class between every instance of our RecyclerViews
 * Not the most elegant solution, but we are working on it
 *
 * Author: Kooper Young
 */

/*
 * Keys are the class name the RV resides in
 * The RV in Fragment_Home is fragment_home_rv
 * The RV in Activity_Show_Details is show_details_rv
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>
{
   private final String key;
   
   //private final Model_Fragment_Home _modelShowSchedule = Model_Fragment_Home.getSingleton();
   //private final Model_Show_Class modelClass = Model_Show_Class.getSingleton();
   //private final Model_Class_Order modelRider = Model_Class_Order.getSingleton();
   
   private final EventRepository eventRepository = new EventRepository();
   public ArrayList<Event> events = new ArrayList<>();

   private final EventClassRepository eventClassRepository = new EventClassRepository();
   private ArrayList<EventClass> eventClasses = new ArrayList<>();
   
   //This is called on a per-instance basis whenever you create a new RV and bind it to an adapter
    public RecyclerViewAdapter(String id)
    {
        super();
        key = id;

        switch(key)
        {
            case "fragment_home_rv":
                events = new ArrayList<>();
                break;
            case "show_details_rv":
                eventClasses = new ArrayList<>();
                break;
        }

    }

    @NonNull @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerViewHolder RVH;
        View view;

        //For now, this is how we are going to pass different layout files in the adapter
        switch(key)
        {
            case "fragment_home_rv":
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell, parent, false);
                RVH = new RecyclerViewHolder(view);
                return RVH;
            case "show_details_rv":
                //TODO - Make this a different layout file
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell, parent, false);
                RVH = new RecyclerViewHolder(view);
                return RVH;
            default:
                //TODO - Make this throw an error or give it a default view
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell, parent, false);
                RVH = new RecyclerViewHolder(view);
                return RVH;
        }
    }

    //So you pass this a view from onCreateViewHolder
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public RecyclerViewHolder(View v) {super(v);}
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position)
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- onBindViewHolder");
        //TODO - Move this inside of a switch case when we have different layout files
        TextView showTV = holder.itemView.findViewById(R.id.showTV);
        TextView dateTV = holder.itemView.findViewById(R.id.dateTV);

        switch(key)
        {
            case "fragment_home_rv":
                try
                {
                    showTV.setText(events.get(position).getEventName());
                    dateTV.setText(events.get(position).getEventTime().toString());
                }
                catch(Exception e)
                {
                    Log.d("ERROR in RecyclerViewAdapter", eventRepository.getEvents().size() + "");
                }
            case "show_details_rv":
                try
                {
                    showTV.setText(eventClasses.get(position).getClassName());
                }
                catch(Exception e)
                {
                    Log.d("ERROR in RecyclerViewAdapter", eventClassRepository.getEventClasses().size() + "");
                }
        }
    }
    public void updateEvents(){
        events.clear();
        events = EventViewModel.getModel().eventMutableLiveData.getValue();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- getItemCount");
        switch (key) {
            case "fragment_home_rv":
                return events.size();
            case "show_details_rv":
                return eventClasses.size();
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
