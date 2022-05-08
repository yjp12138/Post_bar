package com.dao;

import com.Servlet.DBUtil;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author : yjp
 * @Date : 2022/5/8 13:17
 */
class userDaoImplTest {
    Connection conn = DBUtil.getConnection();
    userDaoImpl xxx =new userDaoImpl(conn);

    userDaoImplTest() throws SQLException, ClassNotFoundException {
    }

    @Test
    void block() throws Exception {
        System.out.println(xxx.block("001", "黑户"));
    }
}