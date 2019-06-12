package com.example.ahmet.swallow;

import java.text.DateFormat;

public class Post {


    //    private String mPostId; // PK
//    private String mPostTopicTitle;
//    private String mPostContent;
//    private String mPostDateTime;
//    private int mPostLike;
//    private String mPostLocation;
//    private String mPostLink; // Postun başlığı sanırım
    private String mUserName; // FK
    private String mEser;
    private String mYorum;
    private String mEnlem;
    private String mBoylam;


    public Post(String boylam, String enlem, String eser, String userName, String yorum) {

        this.mEser = eser;
        this.mYorum = yorum;
        this.mUserName = userName;
        this.mEnlem = enlem;
        this.mBoylam = boylam;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getEser() {
        return mEser;
    }

    public void setEser(String eser) {
        mEser = eser;
    }

    public String getYorum() {
        return mYorum;
    }

    public void setYorum(String yorum) {
        mYorum = yorum;
    }

    public String getEnlem() {
        return mEnlem;
    }

    public void setEnlem(String enlem) {
        mEnlem = enlem;
    }

    public String getBoylam() {
        return mBoylam;
    }

    public void setBoylam(String boylam) {
        mBoylam = boylam;
    }
}