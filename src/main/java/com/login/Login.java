package com.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.login.dao.LoginDao;


public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userType = request.getParameter("usertype");
        String uname = request.getParameter("username");
        String passwordStr = request.getParameter("password");

        if (uname != null && passwordStr != null && !uname.isEmpty() && !passwordStr.isEmpty()) {
            int password = Integer.parseInt(passwordStr);
            LoginDao loginDao = new LoginDao();

            if ("student".equals(userType) && loginDao.checkStudent(uname, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", String.valueOf(password)); // Set password as sid
                response.sendRedirect("Student.jsp");
            } else if ("instructor".equals(userType) && loginDao.checkInstructor(uname, password)){
                HttpSession session = request.getSession();
                session.setAttribute("username", String.valueOf(password)); // Set password as instructor_id
                response.sendRedirect("Instructor.jsp");
            }else {
                response.sendRedirect("Login.jsp");
            }
        }
    }
}
