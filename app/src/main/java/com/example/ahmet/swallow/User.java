package com.example.ahmet.swallow;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    private String mUserId; // PK
    private String mUserNickname;
    private String mUserPassword;
    private String mUserEmail;
    private String mUserPhoto;
    private String mAboutMe;
    //User ın begendigi postların listesi eklenecek liste mi olmalı?


    public User(){

    }

    public User(String email, String userNickName, String password) {
        this.mUserEmail = email;
        this.mUserPassword = password;
        this.mUserNickname = userNickName;
    }

    public User(String userId, String userNickname,
                String userPassword, String userEmail,String userPhoto, String aboutMe)
    {
        mUserId = userId;
        mUserNickname = userNickname;
        mUserPassword = userPassword;
        mUserEmail = userEmail;
        mUserPhoto = userPhoto;
        mAboutMe = aboutMe;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getUserNickname() {
        return mUserNickname;
    }

    public void setUserNickname(String userNickname) {
        mUserNickname = userNickname;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }

    public String getUserPhoto() {
        return mUserPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        mUserPhoto = userPhoto;
    }

    public String getAboutMe() {
        return mAboutMe;
    }

    public void setAboutMe(String aboutMe) {
        mAboutMe = aboutMe;
    }

}
