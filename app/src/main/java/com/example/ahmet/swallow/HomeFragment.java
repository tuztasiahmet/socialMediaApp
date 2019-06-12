package com.example.ahmet.swallow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private List<Post> posts = new ArrayList<Post>();

    private TextView mEser;
    private TextView mYorum;
    private String mUserName;
    private ListView mListView;
    private ArrayList<String> mArrayList = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private Firebase myFirebase;

    private Context thiscontext;

    private DatabaseReference mDatabase;
    private ListView listView;
    private ArrayAdapter<CustomAdapter> saveAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        View myView = inflater.inflate(R.layout.activity_home_fragment, container, false);

       // mListView = myView.findViewById(R.id.listView);
        //listView = (ListView) myView.findViewById(R.id.listView);


        thiscontext = container.getContext();
        Firebase.setAndroidContext(thiscontext);

        myFirebase = new Firebase("https://swallow-5c357.firebaseio.com/Posts");

        mAdapter = new ArrayAdapter<String>(
                thiscontext,
                android.R.layout.simple_list_item_1,
                mArrayList
        );


        myFirebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                String myEser = dataSnapshot.child("eser").getValue(String.class);
                String myYorum = dataSnapshot.child("yorum").getValue(String.class);

//                posts.add(new Post(myEser, myYorum, mUserName));
                //populateList();
//                mArrayList.add(myEser);
//                mArrayList.add(myYorum);
//                mAdapter .notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

//        mListView.setAdapter(mAdapter);
        return myView;
    }

//    private void populateList() {
//        saveAdapter = new CustomAdapter(this,posts);
//
//        listView.setAdapter(saveAdapter);
//    }
}
