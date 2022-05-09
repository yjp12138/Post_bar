package com.Servlet; /**
 * @Author : yjp
 * @Date : 2022/5/9 19:54
 */

import com.dao.forumDaoImpl;
import com.dao.userDaoImpl;
import com.google.gson.Gson;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "addTypeServlet", value = "/addTypeServlet")
public class addTypeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            Connection conn = DBUtil.getConnection();
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            forumDaoImpl.addType(id, type);
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

    }
}
