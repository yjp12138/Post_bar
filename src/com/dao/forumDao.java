package com.dao;

import com.pojo.Comment;
import com.pojo.Forum;
import com.pojo.User;

/**
 * @Author : yjp
 * @Date : 2022/4/13 23:10
 */
public interface forumDao {
    //添加论文
    public boolean addForum(Forum forum) throws Exception;
    //删除指定论文
    public int deleteForum(int forumId) throws Exception;
    //添加类别
    public boolean AddType(String id, String type) throws Exception;
    //添加评论
    public boolean addComment(Comment comment) throws Exception;
    //删除评论
    public int deleteComment(int commentId) throws Exception;
}
