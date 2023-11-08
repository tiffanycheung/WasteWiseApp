package com.example.wastewise;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyHolder> {

    Context context;
    List<UserPost> postList;
    private FirebaseAuth firebaseAuth;


    private FirebaseFirestore firebaseStore;

    private String userId;

  /* public PostsAdapter(Context context) {
        this.context = context;
    }*/

    public PostsAdapter(Context context, List<UserPost> postList) {

        this.postList = postList;
        this.context = context;
    }

    String name;
    String email;
    String title;
    String description;
   // String userId;
    String timeStamp;
    String profileImage;
    String uDp;
    Number likesNo;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // inflate layout row_post.xml
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_posts, viewGroup, false);


        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int i) {



        // get Data
        userId = postList.get(i).getUserId();
        email = postList.get(i).getEmail();
        name = postList.get(i).getName();
        title = postList.get(i).getTitle();
        description = postList.get(i).getDescription();
        timeStamp = postList.get(i).getTimeStamp();
        likesNo = postList.get(i).getLikesNo();
        profileImage = postList.get(i).getPhotoUrl();

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
        holder.likeTxt.setText(String.valueOf(likesNo));


        int profileImageResourceId = context.getResources().getIdentifier(profileImage, "drawable", context.getPackageName());
       // Drawable drawable = context.getResources().getDrawable(profileImageResourceId);
        holder.uPictureIv.setImageResource(profileImageResourceId);


        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "More", Toast.LENGTH_SHORT).show();

            }
        });

        UserPost userPost = postList.get(i);
        String documentId = userPost.getDocumentId();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference postsCollection = db.collection("posts");
        DocumentReference newPostRef = postsCollection.document(documentId);

        firebaseAuth = FirebaseAuth.getInstance();

        //Set thumb to blue if liked by user before
        isLikedByCurrentUser(documentId, userId, isLiked -> {
            if (isLiked) {
                //button is blue by default as user has liked post before
                holder.likeBtn.setColorFilter(R.color.blue);
        } else {
                holder.likeBtn.setColorFilter(null);
            }});

        //On Click listener for like button
        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLikedByCurrentUser(documentId, userId, isLiked -> {
                    if (isLiked) {

                        holder.likeBtn.setColorFilter(null);

                        newPostRef.update("likes", FieldValue.arrayRemove(userId));

                        Toast.makeText(view.getContext(), "Unlike", Toast.LENGTH_SHORT).show();

                        newPostRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    Number currentLikesNo = (Number) documentSnapshot.get("likesNo");
                                    if (currentLikesNo != null) {
                                        Number newLikesNo = currentLikesNo.intValue() -1 ;


                                        // Update the number of likes in Database
                                        newPostRef.update("likesNo", newLikesNo)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        holder.likeTxt.setText(String.valueOf(newLikesNo));

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });
                                    }


                                }
                            }});


                    } else {

                        holder.likeBtn.setColorFilter(R.color.blue);
                        Toast.makeText(view.getContext(), "Like", Toast.LENGTH_SHORT).show();


                        newPostRef.update("likes", FieldValue.arrayUnion(userId));

                        //  userId = firebaseAuth.getCurrentUser().getUid();

                        newPostRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {

                                    Number currentLikesNo = (Number) documentSnapshot.get("likesNo");
                                    //Long currentLikesNo = documentSnapshot.getLong("likesNo");
                                    if (currentLikesNo != null) {
                                        Number newLikesNo = currentLikesNo.intValue() + 1;


                                        // Update the number of likes in Database
                                        newPostRef.update("likesNo", newLikesNo)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                        holder.likeTxt.setText(String.valueOf(newLikesNo));

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });
                                    }


                                }
                            }});

                    }
                });
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
                String titleShare = holder.pTitleTv.getText().toString().trim();
                String descriptionShare = holder.pDescriptionTv.getText().toString().trim();

                sharePost(view.getContext(), titleShare, descriptionShare);
               //Toast.makeText(view.getContext(), "Share", Toast.LENGTH_SHORT).show();

            }
        });

        holder.postLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PostDetailActivity.class);
                intent.putExtra("postId", documentId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    private void sharePost(Context context, String title, String description){

        String shareBodyText = title +"\n" + description;
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        //For sharing via an email app
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here:");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBodyText);


        context.startActivity((Intent.createChooser(shareIntent, "Share Via")));

    }


    //See if user liked post

    public void isLikedByCurrentUser(String documentId, String currentUserId, OnSuccessListener<Boolean> onSuccess) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference postRef = db.collection("posts").document(documentId);

        postRef.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                List<String> likes = (List<String>) documentSnapshot.get("likes");
                if (likes != null) {
                    boolean isLiked = likes.contains(currentUserId);
                    onSuccess.onSuccess(isLiked);
                } else {
                    onSuccess.onSuccess(false); // Post has no likes yet
                }
            } else {
                onSuccess.onSuccess(false); // Post does not exist
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

        LinearLayout postLayout;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            //initialisation
            uPictureIv = itemView.findViewById(R.id.uPictureIV);
           // pImageIv = itemView.findViewById(R.id.pImageIv);
            pTitleTv = itemView.findViewById(R.id.pTitleTv);
            pTimeTv = itemView.findViewById(R.id.timestampTv);
            uNameTv = itemView.findViewById(R.id.pNameTv);
            pDescriptionTv = itemView.findViewById(R.id.pDescriptionTv);
            //pLikesTv = itemView.findViewById(R.id.likeTxt);
            moreBtn = itemView.findViewById(R.id.moreBtn);
            likeBtn = itemView.findViewById(R.id.likeBtn);
            commentBtn = itemView.findViewById(R.id.commentBtn);
            shareBtn = itemView.findViewById(R.id.shareBtn);
            likeTxt = itemView.findViewById(R.id.likeTxt);
            postLayout = itemView.findViewById(R.id.postLayout);


        }
    }
}
