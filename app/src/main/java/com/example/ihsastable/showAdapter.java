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

    private showModel model = showModel.getSingleton();
    public showAdapter ()
    {
        super();
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
        showTV.setText(model.getTaskArray().get(position).getShow());
    }

    @Override
    public int getItemCount()
    {
        return model.getTaskArray().size();
    }
}
