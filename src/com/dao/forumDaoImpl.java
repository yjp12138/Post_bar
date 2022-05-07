package com.dao;

import com.pojo.Comment;
import com.pojo.Forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * @Author : yjp
 * @Date : 2022/4/13 23:14
 */
public class forumDaoImpl implements forumDao{
    private Connection conn = null; // 数据库连接对象
    private PreparedStatement pstmt = null; // 数据库操作对象
    ResultSet rs = null;
    // 通过构造方法取得数据库连接
    public forumDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean addForum(Forum forum) throws Exception {
        String sql = "insert into post_bar.posts(title, content, post_type, author) values(?,?,?,?)";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setString(1, forum.getTitle());
        this.pstmt.setString(2, forum.getContent());
        this.pstmt.setString(3, forum.getType());
        this.pstmt.setString(4, forum.getAuthor());
        result = this.pstmt.executeUpdate();//执行数据库操作
        if (result == 1){
            pstmt.close();
            return true;
        }
        return false;
    }

    @Override
    public int deleteForum(int forumId) throws Exception {
        String sql = "delete from post_bar.posts where post_id = ?";
        String sql1 = "delete from post_bar.comments where post_id = ?";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setInt(1, forumId);
        result = this.pstmt.executeUpdate();//执行数据库操作
        this.pstmt.close();
        this.pstmt = this.conn.prepareStatement(sql1);
        this.pstmt.setInt(1,forumId);
        this.pstmt.close();
        return result;
    }

    @Override
    public boolean AddType(String id, String type) throws Exception {
        String sql = "update post_bar.posts set post_type = ? where post_id = ?";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setString(1, type);
        this.pstmt.setString(2, id);
        result = this.pstmt.executeUpdate();//执行数据库操作
        if (result == 1){
            pstmt.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean addComment(Comment comment) throws Exception {
        String sql = "insert into post_bar.comments(post_id, id, comme, father_id) values(?,?,?,?)";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setInt(1, comment.getForumId());
        this.pstmt.setString(2, comment.getId());
        this.pstmt.setString(3, comment.getComme());
        this.pstmt.setString(4, comment.getFatherId());
        result = this.pstmt.executeUpdate();//执行数据库操作
        if (result == 1){
            pstmt.close();
            return true;
        }
        return false;
    }

    @Override
    public int deleteComment(int commentId) throws Exception {
        String sql = "delete from post_bar.comments where comme_id = ? or father_id = ?";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setInt(1, commentId);
        this.pstmt.setInt(2, commentId);
        result = this.pstmt.executeUpdate();//执行数据库操作
        this.pstmt.close();
        return result;
    }
}
