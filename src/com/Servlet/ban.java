package com.Servlet; /**
 * @Author : yjp
 * @Date : 2022/5/9 22:40
 */

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
import com.dao.forumDaoImpl;
import com.dao.userDaoImpl;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "ban", value = "/ban")
public class ban extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String identity = request.getParameter("identity");
        try {
            Connection conn = DBUtil.getConnection();
            userDaoImpl userDaoImpl = new userDaoImpl(conn);
            userDaoImpl.block(id, identity);
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
