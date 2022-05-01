package com.dao;

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
    public int addForum(Forum forum) throws Exception {
        String sql = "insert into post_bar.posts(title, content, author) values(?,?,?)";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setString(2, forum.getTitle());
        this.pstmt.setString(3, forum.getContent());
        this.pstmt.setString(4, forum.getAuthor());
        result = this.pstmt.executeUpdate();//执行数据库操作
        this.pstmt.close();
        return result;
    }

    @Override
    public int deleteForum(int forumId) throws Exception {
        String sql = "delete from post_bar.posts where post_id=?";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setInt(1, forumId);
        result = this.pstmt.executeUpdate();//执行数据库操作
        this.pstmt.close();
        return result;
    }
}
