package com.meiko.dao.impl;

import com.meiko.utils.JdbcUtil;
import com.meiko.dao.AdminDao;
import com.meiko.entity.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Meiko on 2019/4/28 12:39
 */
public class AdminDaoImpl implements AdminDao {
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public void save(Admin admin) throws SQLException {
        String sql = "INSERT INTO admin1(userName,pwd) VALUES(?,?);";
        con = JdbcUtil.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, admin.getUserName());
        pstmt.setString(2, admin.getPwd());
        pstmt.executeUpdate();
        JdbcUtil.close(con,pstmt);
    }

    @Override
    public Admin findByNameAndPwd(Admin admin) throws SQLException {
        String sql = "select * from admin1 where userName=? and pwd=?";
        Admin ad = new Admin();
        con = JdbcUtil.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1,admin.getUserName());
        pstmt.setString(2,admin.getPwd());
        rs = pstmt.executeQuery();
        while (rs.next()) {
            ad.setId(rs.getInt("id"));
            ad.setUserName(rs.getString("userName"));
            ad.setPwd(rs.getString("pwd"));
        }
        JdbcUtil.close(con,pstmt,rs);
        return ad;
    }

    @Override
    public boolean userExists(String name) throws SQLException {
        String sql = "select id from admin1 where userName=?";
        con = JdbcUtil.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, name);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            if (id > 0) {
                return true;
            }
        }
        JdbcUtil.close(con,pstmt,rs);
        return false;
    }
}
