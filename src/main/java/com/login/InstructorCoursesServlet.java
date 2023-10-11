package com.login;

import com.google.gson.Gson;
import com.login.dao.InstructorDao;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/InstructorCoursesServlet")
public class InstructorCoursesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String instructorIdStr = (String) session.getAttribute("username");

        if (instructorIdStr != null && !instructorIdStr.isEmpty()) {
            try {
                int instructorId = Integer.parseInt(instructorIdStr);
                InstructorDao instructorDao = new InstructorDao();
                List<Map<String, Object>> instructorCourses = instructorDao.getInstructorCourses(instructorId);

                Gson gson = new Gson();
                String json = gson.toJson(instructorCourses);

                out.print(json);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print(getErrorJson("Invalid instructor ID format."));
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.print(getErrorJson("An error occurred while fetching instructor courses."));
            } finally {
                out.flush();
                out.close();
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(getErrorJson("Instructor ID parameter not provided."));
            out.flush();
            out.close();
        }
    }

    private String getErrorJson(String errorMessage) {
        Gson gson = new Gson();
        return gson.toJson(Map.of("error", errorMessage));
    }
}
