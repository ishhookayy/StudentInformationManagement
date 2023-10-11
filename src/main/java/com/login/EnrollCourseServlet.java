package com.login;

import com.login.dao.StudentDao;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EnrollCourseServlet")
public class EnrollCourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        String studentId = (String) session.getAttribute("username");
        String courseId = request.getParameter("courseId");

        JsonObject jsonResponse = new JsonObject();

        if (studentId != null && !studentId.isEmpty()) {
            try {
                StudentDao studentDao = new StudentDao();
                boolean enrollmentSuccess = studentDao.enrollInCourse(Integer.parseInt(studentId), courseId);

                if (enrollmentSuccess) {
                    jsonResponse.addProperty("success", true);
                } else {
                    jsonResponse.addProperty("success", false);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                jsonResponse.addProperty("success", false);
            } catch (Exception e) {
                e.printStackTrace();
                jsonResponse.addProperty("success", false);
            } finally {
                out.print(new Gson().toJson(jsonResponse));
                out.flush();
                out.close();
            }
        } else {
            jsonResponse.addProperty("success", false);
            out.print(new Gson().toJson(jsonResponse));
            out.flush();
            out.close();
        }
    }
}
