package com.example.wastewise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Forum extends AppCompatActivity {

    ImageView addPostBtn;

    RecyclerView recyclerView;
    List<UserPost> postList;

    PostsAdapter postsAdapter;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // hide action bar
        getSupportActionBar().hide();
        setContentView(R.layout.forum);

        //Initialisations
        recyclerView = findViewById(R.id.postalRecyclerView);
        addPostBtn = findViewById(R.id.addPostBtn);

        //set up recycler view
        postList = new ArrayList<>();
        postsAdapter = new PostsAdapter(postList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(postsAdapter);

        loadPosts();

        // When "Add posts" clicked go to Add Post Activity

        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddPostActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void loadPosts() {
        //path of posts
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference postsCollection = db.collection("posts");
       // DocumentReference newPostRef = postsCollection.document();

        //get all data from this ref
        Query query = postsCollection.orderBy("timestamp", Query.Direction.DESCENDING);



        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Query was successful, you can access the documents
                for (QueryDocumentSnapshot document : task.getResult()) {
                    // Access data for each document
                    // Process postData
                    String name, email,title, description, userId, timeStamp;
                    name = document.getString("name");
                    email = document.getString("email");
                    title = document.getString("title");
                    description = document.getString("description");
                    userId = document.getString("userId");
                    timeStamp = document.getString("timestamp");

                    UserPost post = new UserPost(name,email,title, description, userId, timeStamp);
                    postList.add(post);

                }

                postsAdapter.notifyDataSetChanged();
            } else {

                Log.e("Forum", "Error loading posts: " + task.getException());

                // Handle any errors
            }
        });


    }
}
