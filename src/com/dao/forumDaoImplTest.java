package com.dao;

import com.Servlet.DBUtil;
import com.google.gson.Gson;
import com.pojo.Comment;
import com.pojo.Forum;

import javax.tools.JavaCompiler;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author : yjp
 * @Date : 2022/5/8 0:39
 */
class forumDaoImplTest {
    Connection conn = DBUtil.getConnection();
    forumDaoImpl xxx =new forumDaoImpl(conn);

    forumDaoImplTest() throws SQLException, ClassNotFoundException {
    }

    @org.junit.jupiter.api.Test
    void addForum() throws Exception{
        Forum forum = new Forum();
        forum.setAuthor("123");
        System.out.println(xxx.addForum(forum));
    }

    @org.junit.jupiter.api.Test
    void deleteForum() throws Exception{
        int a = 131;
        System.out.println(xxx.deleteForum(a));
    }

    @org.junit.jupiter.api.Test
    void addType() throws Exception{
        System.out.println(xxx.addType(132, "JAVA"));
    }

    @org.junit.jupiter.api.Test
    void findInfoAll() throws Exception {
        List<Forum> forumList = new ArrayList<>();
        forumList = xxx.FindInfoAll();
        System.out.println(forumList.toString());
    }

    @org.junit.jupiter.api.Test
    void findInfoBY() throws Exception {
        List<Forum> forumList = new ArrayList<>();
        Forum forum = new Forum();
        forum.setAuthor("123");
        System.out.println(xxx.FindInfoBY(forum).get(1).getIssuedTime());
    }

    @org.junit.jupiter.api.Test
    void addComment() throws Exception {
        Comment comment = new Comment();
        comment.setForumId(123);
        comment.setComme("aaa");
        comment.setId("001");
        comment.setFatherId(4);
        System.out.println(xxx.addComment(comment));
    }

    @org.junit.jupiter.api.Test
    void deleteComment() throws Exception{
        System.out.println(xxx.deleteComment(4));
    }

    @org.junit.jupiter.api.Test
    void exportComments() throws Exception {
        System.out.println(xxx.ExportComments(123).get(0).getIssuedTime());
    }
}