package com.Servlet; /**
 * @Author : yjp
 * @Date : 2022/4/18 20:18
 */

/*import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;*/
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "forumServlet", value = "/forumServlet")
public class forumServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
