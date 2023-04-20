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
import com.example.ihsastable.data.model.Rider;
import com.example.ihsastable.data.repository.EventRepository;
import com.example.ihsastable.viewmodel.EventClassesViewModel;
import com.example.ihsastable.viewmodel.EventViewModel;
import com.example.ihsastable.viewmodel.RidersViewModel;

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
   public ArrayList<Rider> riders = new ArrayList<>();
   
   private final EventRepository eventRepository = new EventRepository();
   
   //This is called on a per-instance basis whenever you create a new RV and bind it to an adapter
    public RecyclerViewAdapter(final String id)
    {
        this.key = id;

        switch(this.key) {
            case "fragment_home_rv":
                this.events = new ArrayList<>();
                break;
        }

    }

    @NonNull @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType)
    {
        final RecyclerViewHolder RVH;
        final View view;

        //For now, this is how we are going to pass different layout files in the adapter
        if(this.key.equals("fragment_home_rv"))
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell_holder, parent, false);
            RVH = new RecyclerViewHolder(view);
            return RVH;
        }
        else if (this.key.equals("show_details_rv"))
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_class_holder, parent, false);
            RVH = new RecyclerViewHolder(view);
            return RVH;
        }
        else if (this.key.equals("rider_order_rv"))
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rider_order_holder, parent, false);
            RVH = new RecyclerViewHolder(view);
            return RVH;
        }
        //TODO - Change this to return an error code and deal with it
        else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell_holder, parent, false);
            RVH = new RecyclerViewHolder(view);
            return RVH;
        }
    }

    //So you pass this a view from onCreateViewHolder
    public static class RecyclerViewHolder extends RecyclerView.ViewHolder
    {
        public RecyclerViewHolder(final View v)
        {
            super(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position)
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- onBindViewHolder");


        if(this.key.equals("fragment_home_rv"))
        {
            try
            {
                final TextView showTV = holder.itemView.findViewById(R.id.showTV);
                final TextView dateTV = holder.itemView.findViewById(R.id.dateTV);
                showTV.setText(this.events.get(position).getEventName());
                dateTV.setText(this.events.get(position).getEventTime().toString());
            }
            catch(final Exception e)
            {
                Log.d("test", this.eventRepository.getEvents().size() + "");
            }
        } else if (this.key.equals("show_details_rv")) {
                final TextView className = holder.itemView.findViewById(R.id.classNameTV);
                final TextView patternName = holder.itemView.findViewById(R.id.patternNameTV);

                className.setText(EventClassesViewModel.getModel().eventClasses.getValue().get(position).getClassName());
                patternName.setText(EventClassesViewModel.getModel().eventClasses.getValue().get(position).getPattern());

        } else if(this.key.equals("rider_order_rv"))
        {
                final TextView riderName = holder.itemView.findViewById(R.id.riderNameTV);
                final TextView riderId = holder.itemView.findViewById(R.id.riderIdTV);
                final Rider rider =  RidersViewModel.getModel().riders.getValue().get(position);

                riderName.setText(rider.getFirstName() + " " + rider.getLastName());
                // Should use RiderId not userId
                riderId.setText(rider.getId() + "");
        }
        else if(this.key.equals("idkyet")){
            //showTV.setText(modelRider.getOrderArray().get(position).getOrder());
            //dateTV.setText(modelRider.getOrderArray().get(position).getHorse());
        }
    }
    public void updateEvents(){
        this.events.clear();
        this.events = EventViewModel.getModel().eventMutableLiveData.getValue();
        this.notifyDataSetChanged();
    }
    public void updateEventClasses(){
        this.eventClasses.clear();
        this.eventClasses = EventClassesViewModel.getModel().eventClasses.getValue();
        this.notifyDataSetChanged();
    }
    public void updateRiders(){
        this.riders.clear();
        this.riders = RidersViewModel.getModel().riders.getValue();
        Log.d("test", "numRiders " + RidersViewModel.getModel().riders.getValue().size());
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        Log.d("RV ADAPT", "RecyclerViewAdapter --- getItemCount");
        switch (this.key) {
            case "fragment_home_rv":
                //return _modelShowSchedule.getTaskArray().size();
                //Log.d("EVENTREPO", "" + eventRepository.getEvents().size() );
                return this.events.size();
            case "show_details_rv":
                //return modelClass.getSchedArray().size();
                return this.eventClasses.size();
            case "rider_order_rv":
                return this.riders.size();
            case "favorites_rv":
                return 0;
            default:
                //TODO - make this return something a little more useful. This will just crash
                return 0;
        }
    }
}
