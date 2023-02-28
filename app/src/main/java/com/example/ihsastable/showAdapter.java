package com.example.ihsastable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class showAdapter extends RecyclerView.Adapter<showAdapter.showViewHolder>
{
    public static class showViewHolder extends RecyclerView.ViewHolder
    {
        public showViewHolder(View v)
        {
            super(v);
        }
    }

    private int current;
    private showModel modelShow = showModel.getSingleton();
    private classModel modelClass = classModel.getSingleton();
    public showAdapter (int key)
    {
        super();
        current = key;
    }

    @NonNull
    @Override
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
        if(current == 0){
            showTV.setText(modelShow.getTaskArray().get(position).getShow());
        }
        if(current == 1){
            showTV.setText(modelClass.getSchedArray().get(position).getClassModel());
            TextView dateTV = holder.itemView.findViewById(R.id.dateTV);
            dateTV.setText("");
        }

    }

    @Override
    public int getItemCount()
    {
        if(current == 1) {
            return modelClass.getSchedArray().size();
        }
        return modelShow.getTaskArray().size();
    }
}
