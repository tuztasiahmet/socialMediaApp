package com.example.ahmet.swallow;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater postInflater;
    private List<Post> postList;

    public CustomAdapter(Activity activity, List<Post> postlist) {
        postInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        this.postList = postlist;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int i) {
        return postList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = postInflater.inflate(R.layout.custom_layout, null);

        TextView textViewUserName = (TextView) lineView.findViewById(R.id.textViewYorum);
        TextView textViewEser = (TextView) lineView.findViewById(R.id.textViewEser);
        TextView textViewYorum = (TextView) lineView.findViewById(R.id.textViewUserName);

        Post post = postList.get(i);
        textViewUserName.setText(post.getUserName());
        textViewEser.setText(post.getEser());
        textViewYorum.setText(post.getYorum());

        return lineView;
    }
}
