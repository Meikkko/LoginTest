package com.meiko.service.impl;

import com.meiko.dao.AdminDao;
import com.meiko.dao.impl.AdminDaoImpl;
import com.meiko.entity.Admin;
import com.meiko.exception.UserExistsException;
import com.meiko.service.AdminService;

import java.sql.SQLException;

/**
 * @author Meiko on 2019/4/28 14:12
 */
public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public void register(Admin admin) throws SQLException, UserExistsException {
        try {
            boolean flag = adminDao.userExists(admin.getUserName());
            if (flag){
                throw new UserExistsException("用户名已经存在，注册失败！");
            }
            adminDao.save(admin);
        } catch (UserExistsException e) {
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin login(Admin admin) throws SQLException {
        return adminDao.findByNameAndPwd(admin);
    }
}
