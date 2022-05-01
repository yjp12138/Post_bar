package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseJdbc {
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/post_bar";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private Connection conn = null;

    public BaseJdbc() throws Exception { // 进行数据库连接
        try {
            Class.forName(Driver); // 用反射加载数据库驱动
            this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw e; // 抛出异常
        }
    }

    public Connection getConnection() {
        return this.conn; // 取得数据库的连接
    }

    public void close() throws Exception { // 关闭数据库
        if (this.conn != null) {
            try {
                this.conn.close(); // 数据库关闭
            } catch (Exception e) {
                throw e;
            }
        }
    }

}
