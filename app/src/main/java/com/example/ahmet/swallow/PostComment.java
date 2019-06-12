package com.example.ahmet.swallow;

public class PostComment {

    private String mCommentId; // PK
    private String mCommentContent;
    private String mCommentDateTime;
    private int mCommentLike;
    private String mUserId; // FK
    private String mPostId; // FK

    public PostComment(String commentId, String commentContent, String commentDateTime, int commentLike, String userId, String postId) {
        mCommentId = commentId;
        mCommentContent = commentContent;
        mCommentDateTime = commentDateTime;
        mCommentLike = commentLike;
        mUserId = userId;
        mPostId = postId;
    }

    public String getCommentId() {
        return mCommentId;
    }

    public void setCommentId(String commentId) {
        mCommentId = commentId;
    }

    public String getCommentContent() {
        return mCommentContent;
    }

    public void setCommentContent(String commentContent) {
        mCommentContent = commentContent;
    }

    public String getCommentDateTime() {
        return mCommentDateTime;
    }

    public void setCommentDateTime(String commentDateTime) {
        mCommentDateTime = commentDateTime;
    }

    public int getCommentLike() {
        return mCommentLike;
    }

    public void setCommentLike(int commentLike) {
        mCommentLike = commentLike;
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String userId) {
        mUserId = userId;
    }

    public String getPostId() {
        return mPostId;
    }

    public void setPostId(String postId) {
        mPostId = postId;
    }
}
