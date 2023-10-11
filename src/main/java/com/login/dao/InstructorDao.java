package com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.login.DatabaseConfig;

public class InstructorDao {

    public List<Map<String, Object>> getInstructorDetails(int password) {
    	DatabaseConfig config = new DatabaseConfig();
    	String url = config.getDbUrl();
    	String user = config.getDbUser();
    	String pwd = config.getDbPassword();
        String query = "SELECT * FROM instructor WHERE instructor_id = ?";
        List<Map<String, Object>> instructorDetailsList = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, password);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Map<String, Object> instructorDetails = new HashMap<>();
                instructorDetails.put("instructorId", rs.getInt("instructor_id"));
                instructorDetails.put("firstName", rs.getString("first_name"));
                instructorDetails.put("lastName", rs.getString("last_name"));
                instructorDetails.put("salary", rs.getDouble("salary"));
                // Add other instructor details as needed
                instructorDetailsList.add(instructorDetails);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instructorDetailsList;
    }

    public List<Map<String, Object>> getInstructorCourses(int instructorId) {
    	DatabaseConfig config = new DatabaseConfig();
    	String url = config.getDbUrl();
    	String user = config.getDbUser();
    	String pwd = config.getDbPassword();
        String query = "SELECT course.course_id, course.course_name, course.credit_hours " +
                "FROM course " +
                "INNER JOIN course_instructor ON course.course_id = course_instructor.course_id " +
                "WHERE course_instructor.instructor_id = ?";
        List<Map<String, Object>> instructorCourses = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, instructorId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Map<String, Object> course = new HashMap<>();
                course.put("courseId", rs.getString("course_id"));
                course.put("courseName", rs.getString("course_name"));
                course.put("creditHours", rs.getInt("credit_hours"));
                // Add other course details as needed
                instructorCourses.add(course);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instructorCourses;
    }
}
