package com.example.ahmet.swallow;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostFragment extends Fragment {


    private TextView mEnlem;
    private TextView mBoylam;

    private EditText mEser;
    private EditText mComment;

    private Button mAddPhoto;
    private Button mAddFriends;
    private Button mCheckin;

    private String userName;

    String enlem;
    String boylam;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View myView = inflater.inflate(R.layout.activity_post_fragment, container, false);

        mEnlem = myView.findViewById(R.id.enlem);
        mBoylam = myView.findViewById(R.id.boylam);

        mEser = myView.findViewById(R.id.eserPostFragment);
        mComment = myView.findViewById(R.id.commentPostFragment);

        mAddPhoto = myView.findViewById(R.id.addPhotoBtn);
        mAddFriends = myView.findViewById(R.id.addFriendsBtn);
        mCheckin = (Button) myView.findViewById(R.id.checkinBtn);

        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Firebase myFirebase = new Firebase("https://swallow-5c357.firebaseio.com/Users").child(user_id).child("username");

        myFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userName = dataSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        final DatabaseReference current_location_db = FirebaseDatabase.getInstance().getReference().child("Users").
                                                                            child(user_id).child("Location");

        current_location_db.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {

                    enlem = dataSnapshot.child("enlem").getValue().toString();
                    boylam =dataSnapshot.child("boylam").getValue().toString();
                    mEnlem.setText(enlem);
                    mBoylam.setText(boylam);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eser = mEser.getText().toString();
                String comment = mComment.getText().toString();

                DatabaseReference current_post_db = FirebaseDatabase.getInstance().getReference().child("Posts");

                String id =current_post_db.push().getKey();

                Post post = new Post(boylam,enlem, eser, userName , comment);
                current_post_db.child(id).setValue(post);

                mEser.getText().clear();
                mComment.getText().clear();
            }
        });
        return myView;
    }

}
