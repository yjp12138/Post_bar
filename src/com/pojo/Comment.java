package com.pojo;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author : yjp
 * @Date : 2022/5/6 22:04
 */
public class Comment {
    private Integer commentId; //评论id
    private Integer forumId;  //帖子号
    private String id;  //评论者id
    private String comme;  //评论内容
    private Integer fatherId;  //父评论id
    private Timestamp issuedTime;  //发布时间

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
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

    public Integer getFatherId() {
        return fatherId;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
    }

    public Timestamp getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Timestamp issuedTime) {
        this.issuedTime = issuedTime;
    }
}
