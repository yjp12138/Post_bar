package com.Servlet; /**
 * @Author : yjp
 * @Date : 2022/4/18 20:17
 */

import com.dao.userDaoImpl;
import com.google.gson.Gson;
import com.pojo.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "registerServlet", value = "/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(request.getParameter("id"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setRepassword(request.getParameter("repassword"));
        try {
            Connection conn = DBUtil.getConnection();
            userDaoImpl userDaoImpl = new userDaoImpl(conn);
            userDaoImpl.block(user.getId(), user.getUsername());
            PrintWriter oo = response.getWriter();
            response.setCharacterEncoding("utf-8");
            oo.print(new Gson().toJson("上传成功！"));
            oo.flush();
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId(request.getParameter("id"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setIdentity(request.getParameter("identity"));
        try {
            Connection conn = DBUtil.getConnection();
            userDaoImpl userDaoImpl = new userDaoImpl(conn);
            if (userDaoImpl.addUser(user)){
                PrintWriter oo = response.getWriter();
                response.setCharacterEncoding("utf-8");
                oo.print(new Gson().toJson("上传成功！"));
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
