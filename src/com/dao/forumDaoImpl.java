package com.dao;

import com.pojo.Comment;
import com.pojo.Forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<Forum> FindInfoAll() throws Exception {
        List<Forum> forumList = new ArrayList<>();
        String sql = "SELECT * FROM post_bar.posts";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Forum uu = new Forum();
            uu.setForumId(rs.getInt(1));
            uu.setTitle(rs.getString(2));
            uu.setContent(rs.getString(3));
            uu.setType(rs.getString(4));
            uu.setAuthor(rs.getString(5));
            uu.setIssuedTime(rs.getTime(6));
            forumList.add(uu);
        }
        rs.close();
        stmt.close();
        return forumList;
    }

    @Override
    public List<Forum> FindInfoBY(Forum forum) throws Exception {
        int count=0;
        List<Forum> forumList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("author",forum.getAuthor());
        map.put("title",forum.getTitle());
        map.put("type", forum.getType());
        for (String key : map.keySet()) {
            String val = map.get(key);
            if (!val.equals("")) {
                count++;
            }
        }
        String sql = "SELECT * FROM post_bar.posts";
        if (count != 0){
            sql+=" WHERE ";
        }
        for (String key : map.keySet()) {
            String val = map.get(key);
            if (!val.equals("")&& count!=0) {
                sql+=key+"="+"'"+val +"'";
                count--;
                if (count!=0){
                    sql+= " AND ";
                }
            }
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Forum uu = new Forum();
            uu.setForumId(rs.getInt(1));
            uu.setTitle(rs.getString(2));
            uu.setContent(rs.getString(3));
            uu.setType(rs.getString(4));
            uu.setAuthor(rs.getString(5));
            uu.setIssuedTime(rs.getTime(6));
            forumList.add(uu);
        }
        stmt.close();
        rs.close();
        return forumList;
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

    @Override
    public List<Comment> ExportComments(Integer formId) throws Exception {
        int count=0;
        List<Comment> commentList = new ArrayList<>();
        String sql = "select * from post_bar.comments where post_id = ?";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setInt(1, formId);
        rs = pstmt.executeQuery(sql);
        while(rs.next()){
            Comment uu = new Comment();
            uu.setCommentId(rs.getInt(1));
            uu.setForumId(rs.getInt(2));
            uu.setId(rs.getString(3));
            uu.setComme(rs.getString(4));
            uu.setFatherId(rs.getString(5));
            uu.setIssuedTime(rs.getTime(6));
            commentList.add(uu);
        }
        pstmt.close();
        rs.close();
        return commentList;
    }
}
