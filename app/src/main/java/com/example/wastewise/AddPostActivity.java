package com.example.wastewise;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class AddPostActivity extends AppCompatActivity {

    //Views
    EditText titleEt,descriptionEt;
    ImageView imageIv, goBackBtn, profileImage;

    Button uploadBtn;

    TextView nameTxt;

    //User Info

    String name, email, userId;

    private FirebaseAuth firebaseAuth;


    private FirebaseFirestore firebaseStore;

    //progress bar
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        getSupportActionBar().hide();

        pd = new ProgressDialog(this);



        //get current user info

        //initialisation
        titleEt = findViewById(R.id.pTitleEt);
        descriptionEt = findViewById(R.id.pDescriptionEt);
       // imageIv = findViewById(R.id.pImageIv);
        uploadBtn = findViewById(R.id.pUploadBtn);
        goBackBtn = findViewById(R.id.goBackBtn);
        nameTxt = findViewById(R.id.nameTxt);
        profileImage = findViewById(R.id.profileImage);

        //Get Users Name using Firebase

        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseStore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = firebaseStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                name = documentSnapshot.get("fullName").toString();
                email = documentSnapshot.get("email").toString();
                nameTxt.setText(name);
            }
        });

        //Upload Btn Click Listener

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEt.getText().toString().trim();
                String description = descriptionEt.getText().toString().trim();

                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(AddPostActivity.this, "Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                } if (TextUtils.isEmpty(description)) {
                    Toast.makeText(AddPostActivity.this, "Enter Description", Toast.LENGTH_SHORT).show();
                    return;
                }

                uploadData(title, description);

            }
        });

        //Back Button
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPostActivity.this, Forum.class);
                startActivity(intent);
            }
        });
    }

    private void uploadData(String title, String description) {
        pd.setMessage("Publishing post...");
        pd.show();

        String timeStamp = String.valueOf(System.currentTimeMillis());

        String filePathAndName = "Posts/" + "post_" + timeStamp;

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference postsCollection = db.collection("posts");
        DocumentReference newPostRef = postsCollection.document();

        Map<String, Object> hashMap = new HashMap<>();

        //testing

        hashMap.put("title", title);
        hashMap.put("description", description);
        hashMap.put("userId", userId);
        hashMap.put("timestamp",timeStamp);
        hashMap.put("name", name);
        hashMap.put("email", email);
        hashMap.put("likesNo", 0);
        hashMap.put("photoUrl", "profile_pic");



        newPostRef.set(hashMap).addOnSuccessListener(aVoid -> {
            pd.dismiss();
            Toast.makeText(AddPostActivity.this, "Post published", Toast.LENGTH_SHORT).show();

            // Return to Forum Page
            Intent intent = new Intent(getApplicationContext(), Forum.class);
            startActivity(intent);
            //Reset views
            //titleEt.setText("");

        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(AddPostActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
}