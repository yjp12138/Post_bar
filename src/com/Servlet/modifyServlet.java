package com.Servlet; /**
 * @Author : yjp
 * @Date : 2022/5/8 19:11
 */

import com.dao.userDaoImpl;
import com.google.gson.Gson;
import com.pojo.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "modifyServlet", value = "/modifyServlet")
public class modifyServlet extends HttpServlet {
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
            if ((userDaoImpl.updateUser(user) >= 0)) {
                PrintWriter oo = response.getWriter();
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                oo.print(new Gson().toJson("操作成功！"));
                oo.flush();
                oo.close();
            } else {
                System.out.println("未找到该数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String identity = request.getParameter("username");
        try {
            Connection conn = DBUtil.getConnection();
            userDaoImpl userDaoImpl = new userDaoImpl(conn);
            if (userDaoImpl.block(id, identity)) {
                PrintWriter oo = response.getWriter();
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                oo.print(new Gson().toJson("操作成功！"));
                oo.flush();
                oo.close();
            } else {
                System.out.println("未找到该数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
