package com.example.wastewise;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyHolder>{
    Context context;
    List<ForumComment> commentList;

    public CommentsAdapter(Context context, List<ForumComment> commentList) {
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_comments, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {
        String name = commentList.get(i).getName();
        String comment = commentList.get(i).getComment();
        String timestamp = commentList.get(i).getTimestamp();

        String photoImage = commentList.get(i).getuDp();

        if (photoImage == null) {
            photoImage = "new_user";
        }

        //TODO: BELOW and Add variable in forum comment
        //String profileImage = commentList.get(i).getProfileImage();

        //Convert timestamp to dd/mm/yyyy hh:mm am/pm
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
        //set Data
        holder.nameTv.setText(name);
        holder.descriptionTv.setText(comment);
        holder.timeTv.setText(pTime);

        //TODO: Set profile Image
       int profileImageResourceId = context.getResources().getIdentifier(photoImage, "drawable", context.getPackageName());
        // Drawable drawable = context.getResources().getDrawable(profileImageResourceId);
        holder.profileIv.setImageResource(profileImageResourceId);

        //holder.timeTv.setText("heyyy");

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        //declare view from row_comments.xml
        ImageView profileIv;
        TextView nameTv, descriptionTv, timeTv;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            profileIv = itemView.findViewById(R.id.profileIv);
            nameTv = itemView.findViewById(R.id.nameTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTV);
            timeTv = itemView.findViewById(R.id.timeTv);
        }
    }
}
