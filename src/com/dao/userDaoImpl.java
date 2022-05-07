package com.dao;

import com.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author : yjp
 * @Date : 2022/4/13 22:40
 */
public class userDaoImpl implements userDao{
    private Connection conn = null; // 数据库连接对象
    private PreparedStatement pstmt = null; // 数据库操作对象
    ResultSet rs = null;
    // 通过构造方法取得数据库连接
    public userDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean LoginUser(String id, String password, String identity) throws Exception {
        String sql = "select * from post_bar.user";
        Statement stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            if (rs.getString(1).equals(id) && rs.getString(2).equals(password) && rs.getString(4).equals(identity)){
                System.out.println("登录成功");
                stmt.close();
                rs.close();
                return true;
            }
        }
        return false;
    }

    @Override
    public String search(String id) throws Exception {
        String sql = "select username from post_bar.user where id = ?";
        Statement stmt = conn.createStatement();
        this.pstmt.setString(1, id);
        rs = stmt.executeQuery(sql);
        pstmt.close();
        return rs.getString(1);
    }

    @Override
    public int block(String id, String identity) throws Exception {
        String sql = "update post_bar.user set identity =? where id=?";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, identity);
        this.pstmt.setString(2, id);
        result = this.pstmt.executeUpdate();
        this.pstmt.close();
        return result;
    }



    @Override
    public boolean addUser(User user) throws Exception {
        String sql = "insert into post_bar.user values(?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, user.getId());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getUsername());
        stmt.setString(4, user.getIdentity());
        int item = stmt.executeUpdate();
        if (item == 1){
            stmt.close();
            return true;
        }
        return false;
    }

    @Override
    public int deleteUser(String id) throws Exception {
        String sql = "delete from post_bar.user where id=?";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);//获取PreparedStatement对象
        this.pstmt.setString(1, id);
        result = this.pstmt.executeUpdate();//执行数据库操作
        this.pstmt.close();
        return result;
    }

    @Override
    public int updateUser(User user) throws Exception {
        String sql = "update post_bar.user set username =? where id=?";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(3, user.getUsername());
        this.pstmt.setString(2, user.getPassword());
        result = this.pstmt.executeUpdate();
        this.pstmt.close();
        return result;
    }
}
