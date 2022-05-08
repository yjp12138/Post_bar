package com.Servlet; /**
 * @Author : yjp
 * @Date : 2022/4/18 20:16
 */

import com.dao.userDao;
import com.dao.userDaoImpl;
import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String identity = request.getParameter("identity");
        try {
            Connection conn = null;
            conn = DBUtil.getConnection();
            userDaoImpl userDaoImpl = new userDaoImpl(conn);
            if ((userDaoImpl.block(id,identity) != 0)){
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
        String id = request.getParameter("username");
        String password = request.getParameter("password");
        String userfrom = request.getParameter("userfrom");
        System.out.println("前端数据："+id+","+password+","+userfrom);

        try{
            Connection conn = DBUtil.getConnection();
            userDaoImpl userDaoImpl = new userDaoImpl(conn);
            if (userDaoImpl.LoginUser(id, password, userfrom)){
                PrintWriter oo = response.getWriter();
                request.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                oo.print(new Gson().toJson("登陆成功！"));
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
