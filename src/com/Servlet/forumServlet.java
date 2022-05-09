package com.Servlet; /**
 * @Author : yjp
 * @Date : 2022/4/18 20:18
 */

import com.dao.forumDaoImpl;
import com.dao.userDaoImpl;
import com.google.gson.Gson;
import com.pojo.Forum;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "forumServlet", value = "/forumServlet")
public class forumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //add
        Forum forum = new Forum();
        forum.setTitle(request.getParameter("title"));
        forum.setContent(request.getParameter("content"));
        try {
            Connection conn = DBUtil.getConnection();
            userDaoImpl userDaoImpl = new userDaoImpl(conn);
            forum.setAuthor(userDaoImpl.search(request.getParameter("id")));
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            if (forumDaoImpl.addForum(forum)){
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //delete
        try {
            Connection conn = DBUtil.getConnection();
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            if (forumDaoImpl.deleteForum(Integer.parseInt(request.getParameter("forumId"))) >= 0){
                PrintWriter oo = response.getWriter();
                response.setCharacterEncoding("utf-8");
                oo.print(new Gson().toJson("删除成功！"));
                oo.flush();
                oo.close();
            }else{
                System.out.println("未找到该数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
