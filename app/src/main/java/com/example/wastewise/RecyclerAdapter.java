package com.example.wastewise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Checkup> activitiesList;

    public RecyclerAdapter(ArrayList<Checkup> activitiesList) {
        this.activitiesList = activitiesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView activityTxt;
        private ImageView activityIcon;

        public MyViewHolder(final View view) {
            super(view);
            activityTxt = view.findViewById(R.id.activityTxt);
            activityIcon = view.findViewById(R.id.activityIcon);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkup_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String activityName = activitiesList.get(position).getActivityName();
        int activityIcon = activitiesList.get(position).getImage();

        holder.activityTxt.setText(activityName);
        holder.activityIcon.setImageResource(activityIcon);
    }

    @Override
    public int getItemCount() {
        return activitiesList.size();
    }
}
