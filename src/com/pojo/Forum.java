package com.pojo;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @Author : yjp
 * @Date : 2022/4/13 23:00
 */
public class Forum {
    private int forumId;
    private String title;
    private String content;
    private String author;
    private String type;
    private Timestamp issuedTime;

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(Timestamp issuedTime) {
        this.issuedTime = issuedTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
