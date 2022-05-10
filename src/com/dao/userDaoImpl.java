package com.dao;

import com.pojo.Forum;
import com.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
        String a;
        String sql = "select username from post_bar.user where id = "+ id;
        this.pstmt = this.conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while(rs.next()){
             a = rs.getString(1);
            return rs.getString(1);
        }

        pstmt.close();
        rs.close();
        return rs.getString(1);
    }

    @Override
    public boolean block(String id, String identity) throws Exception {
        String sql = "update post_bar.user set identity =? where id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, identity);
        stmt.setString(2, id);
        int item = stmt.executeUpdate();
        stmt.close();
        if (item == 1){
            return true;
        }
        return false;
    }

    @Override
    public List<User> findAll() throws Exception {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM post_bar.user";
        this.pstmt = this.conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while(rs.next()){
            User uu = new User();
            uu.setId(rs.getString(1));
            uu.setPassword(rs.getString(2));
            uu.setUsername(rs.getString(3));
            uu.setIdentity(rs.getString(4));
            users.add(uu);
        }
        rs.close();
        pstmt.close();
        return users;
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
        String sql = "update post_bar.user,post_bar.posts set username =?,author = ? where id=? and post_bar.posts.author=post_bar.user.username";
        int result = 0;
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getUsername());
        this.pstmt.setString(2, user.getUsername());
        this.pstmt.setString(3, user.getId());
        result = pstmt.executeUpdate();
        pstmt.close();
        return result;
    }
}
