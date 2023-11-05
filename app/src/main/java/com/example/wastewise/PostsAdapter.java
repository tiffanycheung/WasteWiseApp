package com.example.wastewise;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyHolder> {

    Context context;
    List<UserPost> postList;

    public PostsAdapter(Context context) {
        this.context = context;
    }

    public PostsAdapter(List<UserPost> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // inflate layout row_post.xml
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_posts, viewGroup, false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int i) {

        String name, email,title, description, userId, timeStamp, uDp;

        // get Data
        userId = postList.get(i).getUserId();
        email = postList.get(i).getEmail();
        name = postList.get(i).getName();
        title = postList.get(i).getTitle();
        description = postList.get(i).getDescription();
        timeStamp = postList.get(i).getTimeStamp();
       // uDp = postList.get(i).getDp();

        //Convert timestamp to dd/mm/yyyy hh:mm am/pm
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(timeStamp));
        String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
        //set Data
        holder.uNameTv.setText(name);
        holder.pTimeTv.setText(pTime);
        holder.pTitleTv.setText(title);
        holder.pDescriptionTv.setText(description);



        //handle Button clicks
        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();
            }
        });

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();


            }
        });

        holder.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Comment", Toast.LENGTH_SHORT).show();


            }
        });

        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Share", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    // view holder class

    class MyHolder extends RecyclerView.ViewHolder{

        // views from row_post.xml

        ImageView uPictureIv, pImageIv;
        TextView uNameTv, pTimeTv, pTitleTv, pDescriptionTv, pLikesTv, likeTxt, commentTxt, shareTxt;
        ImageButton moreBtn;
        //Button likeBtn, commentBtn, shareBtn;

        ImageView likeBtn, commentBtn, shareBtn;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //initialisation
            uPictureIv = itemView.findViewById(R.id.uPictureIV);
           // pImageIv = itemView.findViewById(R.id.pImageIv);
            pTitleTv = itemView.findViewById(R.id.pTitleTv);
            pTimeTv = itemView.findViewById(R.id.timestampTv);
            uNameTv = itemView.findViewById(R.id.pNameTv);
            pDescriptionTv = itemView.findViewById(R.id.pDescriptionTv);
            pLikesTv = itemView.findViewById(R.id.likeTxt);
            moreBtn = itemView.findViewById(R.id.moreBtn);
            likeBtn = itemView.findViewById(R.id.addPostBkBtn);
            commentBtn = itemView.findViewById(R.id.commentBtn);
            shareBtn = itemView.findViewById(R.id.shareBtn);


        }
    }
}
