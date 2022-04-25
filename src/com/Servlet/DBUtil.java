package com.Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 工具包类：提供数据库链接接口
public class DBUtil {
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/forum?serverTimezone=GMT%2B8";
        Connection conn = DriverManager.getConnection(url, "root", "123456");
        return conn;
    };
}
