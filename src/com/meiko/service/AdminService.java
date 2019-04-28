package com.meiko.service;

import com.meiko.entity.Admin;
import com.meiko.exception.UserExistsException;

import java.sql.SQLException;

/**
 * 业务逻辑层
 * @author Meiko on 2019/4/28 14:08
 */
public interface AdminService {
    /**
     * 注册
     */
    void register(Admin admin) throws SQLException, UserExistsException;

     /**
     * 登陆
     */
    Admin login(Admin admin) throws SQLException;
}
