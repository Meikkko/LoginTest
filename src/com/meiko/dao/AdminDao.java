package com.meiko.dao;

import com.meiko.entity.Admin;

import java.sql.SQLException;

/**
 * @author Meiko on 2019/4/28 11:35
 */
public interface AdminDao {
    void save(Admin admin) throws SQLException;

    Admin findByNameAndPwd(Admin admin) throws SQLException;

    boolean userExists(String name) throws SQLException;

}
