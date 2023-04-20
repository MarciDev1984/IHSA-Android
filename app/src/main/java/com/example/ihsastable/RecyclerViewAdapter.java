package com.example.ihsastable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihsastable.data.model.Event;
//import com.example.ihsastable.data.model.Events;
import com.example.ihsastable.data.model.EventClass;
import com.example.ihsastable.data.repository.EventRepository;
import com.example.ihsastable.viewmodel.EventClassesViewModel;
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
   public ArrayList<Event> events = new ArrayList<>();
   public ArrayList<EventClass> eventClasses = new ArrayList<>();
   
   private final Model_Fragment_Home _modelShowSchedule = Model_Fragment_Home.getSingleton();
   private final Model_Show_Class modelClass = Model_Show_Class.getSingleton();
   private final Model_Class_Order modelRider = Model_Class_Order.getSingleton();
   
   private final EventRepository eventRepository = new EventRepository();
   
   //This is called on a per-instance basis whenever you create a new RV and bind it to an adapter
    public RecyclerViewAdapter(String id)
    {
        super();
        key = id;

        switch(key) {
            case "fragment_home_rv":
                events = new ArrayList<>();
                break;
        }

    }

    @NonNull @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        RecyclerViewHolder RVH;
        View view;

        //For now, this is how we are going to pass different layout files in the adapter
        if(key.equals("fragment_home_rv"))
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell, parent, false);
            RVH = new RecyclerViewHolder(view);
            return RVH;
        }
        else if (key.equals("show_details_rv"))
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_class, parent, false);
            RVH = new RecyclerViewHolder(view);
            return RVH;
        }
        //TODO - Change this to return an error code and deal with it
        else{
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


        if(key.equals("fragment_home_rv"))
        {
            try
            {
                TextView showTV = holder.itemView.findViewById(R.id.showTV);
                TextView dateTV = holder.itemView.findViewById(R.id.dateTV);
                showTV.setText(events.get(position).getEventName());
                dateTV.setText(events.get(position).getEventTime().toString());
            }
            catch(Exception e)
            {
                Log.d("test", eventRepository.getEvents().size() + "");
            }
        } else if (key.equals("show_details_rv")) {
                TextView className = holder.itemView.findViewById(R.id.classNameTV);
                TextView patternName = holder.itemView.findViewById(R.id.patternNameTV);

                className.setText(EventClassesViewModel.getModel().eventClasses.getValue().get(position).getClassName());
                patternName.setText(EventClassesViewModel.getModel().eventClasses.getValue().get(position).getPattern());

        } else if(key.equals("rider_order_rv"))
        {
            //showTV.setText(modelClass.getSchedArray().get(position).getClassModel());
            //
        }
        else if(key.equals("idkyet")){
            //showTV.setText(modelRider.getOrderArray().get(position).getOrder());
            //dateTV.setText(modelRider.getOrderArray().get(position).getHorse());
        }
    }
    public void updateEvents(){
        events.clear();
        events = EventViewModel.getModel().eventMutableLiveData.getValue();
        notifyDataSetChanged();
    }
    public void updateEventClasses(){
        eventClasses.clear();
        eventClasses = EventClassesViewModel.getModel().eventClasses.getValue();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- getItemCount");
        switch (key) {
            case "fragment_home_rv":
                //return _modelShowSchedule.getTaskArray().size();
                //Log.d("EVENTREPO", "" + eventRepository.getEvents().size() );
                return events.size();
            case "show_details_rv":
                //return modelClass.getSchedArray().size();
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
