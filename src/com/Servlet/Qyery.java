package com.Servlet;

import com.dao.forumDaoImpl;
import com.dao.userDaoImpl;
import com.google.gson.Gson;
import com.pojo.Forum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Query", value = "/Query")
public class Qyery extends HttpServlet {
    //find all
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Forum> forumList = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            forumList = forumDaoImpl.FindInfoAll();
            if (!forumList.isEmpty()){
                PrintWriter pp = response.getWriter();
                response.setCharacterEncoding("utf-8");
                pp.print(new Gson().toJson(forumList));
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
        //find by
        List<Forum> forumList = new ArrayList<>();
        Forum find = new Forum();
        find.setTitle(request.getParameter("title"));
        find.setAuthor(request.getParameter("author"));
        find.setType(request.getParameter("type"));
        try {
            Connection conn = DBUtil.getConnection();
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            forumList = forumDaoImpl.FindInfoBY(find);
            if (!forumList.isEmpty()){
                PrintWriter oo = response.getWriter();
                response.setCharacterEncoding("utf-8");
                oo.print(new Gson().toJson(forumList));
                oo.flush();
                oo.close();
            }else{
                response.sendError(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
