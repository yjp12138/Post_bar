package com.Servlet; /**
 * @Author : yjp
 * @Date : 2022/4/18 20:18
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.dao.forumDaoImpl;
import com.dao.userDaoImpl;
import com.google.gson.Gson;
import com.pojo.Comment;
//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
//import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "commentServlet", value = "/commentServlet")
public class commentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //comment
        Comment comment = new Comment();
        comment.setForumId(Integer.parseInt(request.getParameter("forumId")));
        comment.setId(request.getParameter("id"));
        comment.setComme(request.getParameter("comme"));
        comment.setFatherId(Integer.parseInt(request.getParameter("fatherId")));
        try {
            Connection conn = DBUtil.getConnection();
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            if (forumDaoImpl.addComment(comment)){
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
    //deleteComments
        try {
            Connection conn = DBUtil.getConnection();
            forumDaoImpl forumDaoImpl = new forumDaoImpl(conn);
            if (forumDaoImpl.deleteComment(Integer.parseInt(request.getParameter("commentId"))) != 0){
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
