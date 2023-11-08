package com.example.wastewise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PostDetailActivity extends AppCompatActivity {
    //views
    ImageView uPictureIv, pImageIv, goBackBtn;
    TextView nameTv, pTimeTiv, pTitleTv, pDescriptionTv, pLikesTv, commentTxt;
    ImageButton moreBtn;
    Button likeBtn, shareBtn;
    LinearLayout profileLayout;

    EditText commentEt;
    ImageView sendBtn;
    ImageView commentPic;

    ProgressDialog pd;

    //to get detail of user and post
    String myUid, myEmail, myName, myDp, postId, pLikes, hisDp, hisName, profileImage;

    private FirebaseAuth firebaseAuth;

    private DocumentReference documentReference;


    private FirebaseFirestore firebaseStore;

    RecyclerView recyclerView;

    List<ForumComment> commentList;

    CommentsAdapter commentsAdapter;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_post_detail);

        //get id of posts using intent
        Intent intent = getIntent();
        postId = intent.getStringExtra("postId");

        //initialise views

        uPictureIv = findViewById(R.id.uPictureIV);
        // pImageIv = itemView.findViewById(R.id.pImageIv);
        pTitleTv = findViewById(R.id.pTitleTv);
        pTimeTiv = findViewById(R.id.timestampTv);
        nameTv = findViewById(R.id.pNameTv);
        pDescriptionTv = findViewById(R.id.pDescriptionTv);
        pLikesTv = findViewById(R.id.likeTxt);
        moreBtn = findViewById(R.id.moreBtn);
        commentEt = findViewById(R.id.commentEt);
        sendBtn = findViewById(R.id.sendBtn);
        commentPic = findViewById(R.id.commentPic);
        sendBtn = findViewById(R.id.sendBtn);
        commentTxt = findViewById(R.id.commentTxt);
        recyclerView = findViewById(R.id.recyclerView);
        goBackBtn = findViewById(R.id.goBackBtn);

        loadComments();
        loadPostInfo(postId);
        loadUserInfo();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseStore = FirebaseFirestore.getInstance();

        documentReference = firebaseStore.collection("posts").document(postId);
        context = this;

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostDetailActivity.this, Forum.class);
                startActivity(intent);
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment = commentEt.getText().toString().trim();

                if (TextUtils.isEmpty(comment)) {
                    commentEt.setError("Please Enter a Comment.");
                } else {
                    String timeStamp = String.valueOf(System.currentTimeMillis());

                    CollectionReference commentsRef = documentReference.collection("comments");

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("cId", timeStamp);
                    hashMap.put("comment", comment);
                    hashMap.put("uid", myUid);
                    hashMap.put("name", myName);
                    hashMap.put("profileImage", "profile_pic");

                    //put data in Database
                    commentsRef.add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            // Comment added successfully
                           // pd.dismiss();
                            Toast.makeText(PostDetailActivity.this, "Comment Added.", Toast.LENGTH_SHORT);
                            commentEt.setText("");
                            updateCommentCount();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PostDetailActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT);
                        }
                    });

                }


               /* pd = new ProgressDialog(PostDetailActivity.this);
                pd.setMessage("Adding comment....");*/


                // postComment();
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            myUid = user.getUid();
        } else {
            myUid = "null";
        }


    }

    private void loadComments() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        //CommentsAdapter adapter = new CommentsAdapter();
        recyclerView.setLayoutManager(layoutManager);

        commentList = new ArrayList<>();
        firebaseStore = FirebaseFirestore.getInstance();


        //Get Photo URL of commenter

        DocumentReference documentReference = firebaseStore.collection("posts").document(postId);

        CollectionReference commentsCollection = documentReference.collection("comments");

        Query query = commentsCollection.orderBy("cId", Query.Direction.DESCENDING);

        query.addSnapshotListener((queryDocumentSnapshots, e) -> {
            if (e != null) {
                // Handle any errors here
                return;
            }

            commentList.clear();

            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                // Extract comment data from the document
                String cId = document.getString("cId");
                String comment = document.getString("comment");
                String uid = document.getString("uid");
                String name = document.getString("name");
                //String email = document.getString("name");

                String profileUrl = document.getString("profileImage");

                ForumComment commentItem = new ForumComment(comment, cId, name, profileUrl);
                commentList.add(commentItem);


            }
            if (commentsAdapter == null) {
                commentsAdapter = new CommentsAdapter(getApplicationContext(), commentList);
                recyclerView.setAdapter(commentsAdapter);
            } else {
                commentsAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
            }

        });
    }


    boolean processComment = false;
    private void updateCommentCount() {
        processComment = true;
        DocumentReference documentReference = firebaseStore.collection("posts").document(postId);
        CollectionReference commentsCollection = documentReference.collection("comments");
        commentsCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                int commentCount = task.getResult().size();
                String commentNo = "" + commentCount;
                //  comment count for the post

                Map<String, Object> updateData = new HashMap<>();
                updateData.put("commentCount", commentNo);

                documentReference.update(updateData)
                        .addOnSuccessListener(aVoid -> {
                            commentTxt.setText(commentNo);
                        })
                        .addOnFailureListener(e -> {
                            // Handle the error
                        });
            } else {
                // Handle the error
            }
        });

    }




    private void loadPostInfo(String postId) {
        FirebaseFirestore firebaseStore = FirebaseFirestore.getInstance();
        CollectionReference postsCollection = firebaseStore.collection("posts");
        //DocumentReference newPostRef = postsCollection.document(postId);
        DocumentReference documentReference = firebaseStore.collection("posts").document(postId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null) {

                    pTitleTv.setText(documentSnapshot.get("title").toString());
                    nameTv.setText(documentSnapshot.get("name").toString());
                    pDescriptionTv.setText(documentSnapshot.get("description").toString());
                    pLikesTv.setText(documentSnapshot.get("likesNo").toString());

                    String photoUrl = documentSnapshot.get("photoUrl").toString();

                    int profileImageResourceId = context.getResources().getIdentifier(photoUrl, "drawable", context.getPackageName());
                    uPictureIv.setImageResource(profileImageResourceId);


                    String timeStamp = documentSnapshot.get("timestamp").toString();

                    //Convert timestamp to dd/mm/yyyy hh:mm am/pm
                    Calendar calendar = Calendar.getInstance(Locale.getDefault());
                    calendar.setTimeInMillis(Long.parseLong(timeStamp));
                    String pTime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
                    //set Data
                    pTimeTiv.setText(pTime);

                    //Set Comment Count
                    DocumentReference documentReference = firebaseStore.collection("posts").document(postId);
                    CollectionReference commentsCollection = documentReference.collection("comments");
                    commentsCollection.get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            int commentCount = task.getResult().size();
                            String commentNo = "" + commentCount;
                            commentTxt.setText(commentNo);
                            //  comment count for the post


                        } else {

                        }
                    });
                }
            }
        });
    }


        private void loadUserInfo() {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore firebaseStore = FirebaseFirestore.getInstance();
            if (user != null) {
                myUid = user.getUid();
            }

            DocumentReference documentReference = firebaseStore.collection("users").document(myUid);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                    if (documentSnapshot != null) {
                        myName = documentSnapshot.get("fullName").toString();

                        //TODO: BELOW
                        //profileImage = documentSnapshot.get("profileImage").toString();

                    }
                }


            });


            //myDp
            DocumentReference postRef = firebaseStore.collection("posts").document(postId);

// Retrieve the document
            postRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {
                        // Get the photoUrl field from the document
                         myDp = documentSnapshot.getString("photoUrl");
                        if (myDp != null) {

                        } else {
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                }
            });
        }
    }
