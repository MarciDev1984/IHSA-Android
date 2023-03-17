package com.example.ihsastable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 * This is RecyclerViewAdapter
 * This is a shared class between every instance of our RecyclerViews
 * Not the most elegant solution, but we are working on it
 * TODO - Work on a more elegant solution to this dumpster fire
 *
 * Author: Kooper Young
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.showViewHolder>
{
    public static class showViewHolder extends RecyclerView.ViewHolder
    {
        public showViewHolder(View v)
        {
            super(v);
        }
    }

    private int current;
    private Show_Schedule_Model modelShow = Show_Schedule_Model.getSingleton();
    private classModel modelClass = classModel.getSingleton();
    private riderModel modelRider = riderModel.getSingleton();

    //This is called on a per-instance basis whenever you create a new RV and bind it to an adapter
    public RecyclerViewAdapter(int key)
    {
        super();
        current = key;
    }


    @NonNull @Override
    public showViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_cell, parent, false);
        showViewHolder vh = new showViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull showViewHolder holder, int position)
    {
        TextView showTV = holder.itemView.findViewById(R.id.showTV);
        TextView dateTV = holder.itemView.findViewById(R.id.dateTV);
        if(current == 0){
            showTV.setText(modelShow.getTaskArray().get(position).getShow());
        }
        if(current == 1){
            showTV.setText(modelClass.getSchedArray().get(position).getClassModel());
            dateTV.setText("");
        }
        if(current == 2){
            showTV.setText(modelRider.getOrderArray().get(position).getOrder());
            dateTV.setText(modelRider.getOrderArray().get(position).getHorse());
        }
    }

    @Override
    public int getItemCount()
    {
        if(current == 1) {
            return modelClass.getSchedArray().size();
        } else if (current == 2) {
            return modelRider.getOrderArray().size();
        }
        return modelShow.getTaskArray().size();
    }
}
