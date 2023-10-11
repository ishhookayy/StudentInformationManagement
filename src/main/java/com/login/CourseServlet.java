package com.login;

import com.google.gson.Gson;
import com.login.dao.StudentDao;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String studentId = (String) session.getAttribute("username");

        if (studentId != null && !studentId.isEmpty()) {
            try {
                StudentDao studentDao = new StudentDao();
                List<Map<String, Object>> courses = studentDao.getCourseDetails(Integer.parseInt(studentId));

                Gson gson = new Gson();
                String json = gson.toJson(courses);

                out.print(json);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print(getErrorJson("Invalid student ID format."));
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print(getErrorJson("An error occurred while fetching course details."));
            } finally {
                out.flush();
                out.close();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(getErrorJson("Student ID not found in session."));
            out.flush();
            out.close();
        }
    }

    private String getErrorJson(String errorMessage) {
        Gson gson = new Gson();
        return gson.toJson(Map.of("error", errorMessage));
    }
}