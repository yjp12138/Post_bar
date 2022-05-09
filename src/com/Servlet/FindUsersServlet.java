package com.Servlet;
/**
 * @Author : yjp
 * @Date : 2022/5/8 21:45
 */

import com.dao.forumDaoImpl;
import com.dao.userDaoImpl;
import com.google.gson.Gson;
import com.pojo.Forum;
import com.pojo.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
    }
}
