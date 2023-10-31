package com.example.wastewise;

/* https://www.youtube.com/watch?v=69C1ljfDvl0&ab_channel=CodingWithMitch
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CheckupAdapter extends RecyclerView.Adapter<CheckupAdapter.MyViewHolder> {

    private ArrayList<Checkup> activitiesList;
    private onCheckupListener mOnCheckupListener;

    public CheckupAdapter(ArrayList<Checkup> activitiesList, onCheckupListener onCheckupListener) {
        this.activitiesList = activitiesList;
        this.mOnCheckupListener = onCheckupListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView activityTxt;
        private ImageView activityIcon;
        onCheckupListener onCheckupListener;

        public MyViewHolder(final View itemView, onCheckupListener onCheckupListener) {
            super(itemView);
            activityTxt = itemView.findViewById(R.id.businessTxt);
            activityIcon = itemView.findViewById(R.id.activityIcon);
            this.onCheckupListener = onCheckupListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCheckupListener.onCheckupClick(getAdapterPosition()) ;
        }
    }

    public interface onCheckupListener {
        void onCheckupClick(int position);
    }

    @NonNull
    @Override
    public CheckupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkup_row, parent, false);
        return new MyViewHolder(view, mOnCheckupListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckupAdapter.MyViewHolder holder, int position) {
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
