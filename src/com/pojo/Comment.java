package com.pojo;

import java.sql.Time;
import java.time.LocalDateTime;

/**
 * @Author : yjp
 * @Date : 2022/5/6 22:04
 */
public class Comment {
    private int commentId; //评论id
    private int forumId;  //帖子号
    private String id;  //评论者id
    private String comme;  //评论内容
    private String fatherId;  //父评论id
    private Time issuedTime;  //发布时间

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComme() {
        return comme;
    }

    public void setComme(String comme) {
        this.comme = comme;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public Time getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Time issuedTime) {
        this.issuedTime = issuedTime;
    }
}
