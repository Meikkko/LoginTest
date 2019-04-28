package com.meiko.servlet;

import com.meiko.dao.AdminDao;
import com.meiko.dao.impl.AdminDaoImpl;
import com.meiko.entity.Admin;
import com.meiko.exception.UserExistsException;
import com.meiko.service.AdminService;
import com.meiko.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Meiko on 2019/4/28 14:34
 */
public class AdminServlet extends javax.servlet.http.HttpServlet {

    private AdminService adminService = new AdminServiceImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String method = request.getParameter("method");
        if ("register".equals(method)) {
            register(request,response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //1. 获取请求参数
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        // 封装
        Admin admin = new Admin();
        admin.setUserName(userName);
        admin.setPwd(pwd);

        //2. 调用Service处理注册的业务逻辑
        try {
            adminService.register(admin);
            // 注册成功，跳转到首页
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (UserExistsException e) {
            // 用户名存在，注册失败(跳转到注册页面)
            request.setAttribute("message", "用户名已经存在");
            // 转发
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();  // 测试时候用
            // 其他错误, 跳转到错误页面
            response.sendRedirect(request.getContextPath() + "/error/error.jsp");
        }
    }

}
