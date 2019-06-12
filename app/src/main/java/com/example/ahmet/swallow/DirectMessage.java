package com.example.ahmet.swallow;

import android.support.v7.app.AppCompatActivity;

public class DirectMessage extends AppCompatActivity {

//    private String mDirectMessageId; //PK
    private String mText;
    private String mWho;
    private boolean mWhom; //FK
//    private String mSecondUserId; //FK

    public DirectMessage(String text, String who, boolean whom) {
        this.mText = text;
        this.mWho = who;
        this.mWhom = whom;
    }

//    public String getDirectMessageId() {
//        return mDirectMessageId;
//    }
//
//    public void setDirectMessageId(String directMessageId) {
//        mDirectMessageId = directMessageId;
//    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getWho() {
        return mWho;
    }

    public void setWho(String who) {
        mWho = who;
    }

    public boolean isWhom() {
        return mWhom;
    }

    public void setWhom(boolean whom) {
        mWhom = whom;
    }


//    public String getSecondUserId() {
//        return mSecondUserId;
//    }
//
//    public void setSecondUserId(String secondUserId) {
//        mSecondUserId = secondUserId;
//    }
}
