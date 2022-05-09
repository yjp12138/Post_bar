package com.Servlet;
/**
 * @Author : yjp
 * @Date : 2022/5/8 21:45
 */

import com.dao.forumDaoImpl;
import com.dao.userDaoImpl;
import com.google.gson.Gson;
import com.pojo.Comment;
import com.pojo.Forum;
import com.pojo.User;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FindUsersServlet", value = "/FindUsersServlet")
public class FindUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            userDaoImpl userDaoImpl = new userDaoImpl(conn);
            users = userDaoImpl.findAll();
            if (!users.isEmpty()){
                PrintWriter pp = response.getWriter();
                response.setCharacterEncoding("utf-8");
                pp.print(new Gson().toJson(users));
                pp.flush();
                pp.close();
            }else {
                response.sendError(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Comment> commentList = new ArrayList<>();
        try {
            Connection conn = null; // 数据库连接对象
            PreparedStatement pstmt = null; // 数据库操作对象
            ResultSet rs = null;
            conn = DBUtil.getConnection();
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            String sql = "select post_bar.posts.title,post_bar.comments.id,post_bar.user.username,post_bar.comments.comme,post_bar.comments.issued_time from post_bar.comments,post_bar.posts,post_bar.user where post_bar.comments.post_id = post_bar.posts.post_id and post_bar.user.id=post_bar.comments.id and post_bar.comments.father_id=?";
            int result = 0;
            pstmt = conn.prepareStatement(sql);//获取PreparedStatement对象
            pstmt.setString(1, request.getParameter("id"));
            rs = pstmt.executeQuery();
            while(rs.next()){
                Comment uu = new Comment();
                uu.setTitle(rs.getString(1));
                uu.setId(rs.getString(2));
                uu.setUsername(rs.getString(3));
                uu.setComme(rs.getString(4));
                uu.setIssuedTime(rs.getTimestamp(5));
                commentList.add(uu);
            }
            pstmt.close();
            rs.close();
            PrintWriter pp = response.getWriter();
            response.setCharacterEncoding("utf-8");
            pp.print(new Gson().toJson(commentList));
            pp.flush();
            pp.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
