package com.Servlet;

import com.dao.forumDaoImpl;
import com.google.gson.Gson;
import com.pojo.Comment;
import com.pojo.Forum;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FindComments", value = "/FindComments")
public class FindComments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //find all
        List<Comment> commentList = new ArrayList<>();
        try {
            Connection conn = DBUtil.getConnection();
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            commentList = forumDaoImpl.ExportComments(Integer.parseInt(request.getParameter("forumId")));
            if (!commentList.isEmpty()){
                PrintWriter pp = response.getWriter();
                response.setCharacterEncoding("utf-8");
                pp.print(new Gson().toJson(commentList));
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

    }
}
