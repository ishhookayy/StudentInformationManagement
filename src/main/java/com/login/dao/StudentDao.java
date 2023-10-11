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

public class StudentDao {
	
	//method  to get student details like sid,fname,lname,dob,city
    public List<Map<String, Object>> getStudentDetails(int password) {
    	DatabaseConfig config = new DatabaseConfig();
    	String url = config.getDbUrl();
    	String user = config.getDbUser();
    	String pwd = config.getDbPassword();
        String query = "SELECT * FROM student WHERE sid = ?";
        List<Map<String, Object>> students = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, password);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Map<String, Object> student = new HashMap<>();
                student.put("studentId", rs.getInt("sid"));
                student.put("firstName", rs.getString("fname"));
                student.put("lastName", rs.getString("lname"));
                student.put("dateOfBirth", rs.getDate("dob"));
                student.put("city", rs.getString("city"));
                // Add other student details as needed
                students.add(student);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    //method to get course details to get course_id,course_name,credit_hours
    public List<Map<String, Object>> getCourseDetails(int studentId) {
    	DatabaseConfig config = new DatabaseConfig();
    	String url = config.getDbUrl();
    	String user = config.getDbUser();
    	String pwd = config.getDbPassword();
        String query = "SELECT enrollment.course_id, course.course_name, enrollment.enrollment_date " +
                "FROM enrollment " +
                "JOIN course ON enrollment.course_id = course.course_id " +
                "WHERE enrollment.student_id = ?";
        List<Map<String, Object>> courses = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, studentId); // Set student_id as an integer
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Map<String, Object> course = new HashMap<>();
                course.put("courseId", rs.getString("course_id")); // Retrieve as string
                course.put("courseName", rs.getString("course_name"));
                course.put("enrollmentDate", rs.getDate("enrollment_date")); // Retrieve as date
                // Add other course details as needed
                courses.add(course);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }
    
    //method to check whether the student is already enrolled into a particular course to maintain data consistency this method is imp
    public boolean isEnrolled(int studentId, String courseId) {
    	DatabaseConfig config = new DatabaseConfig();
    	String url = config.getDbUrl();
    	String user = config.getDbUser();
    	String pwd = config.getDbPassword();
        String query = "SELECT COUNT(*) FROM enrollment WHERE student_id = ? AND course_id = ?";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, studentId);
            st.setString(2, courseId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //method to enroll into a particular course using both student_id and course_id only if valid
    public boolean enrollInCourse(int studentId, String courseId) {
    	DatabaseConfig config = new DatabaseConfig();
    	String url = config.getDbUrl();
    	String user = config.getDbUser();
    	String pwd = config.getDbPassword();

        // Check if the student is already enrolled in the course
        if (isEnrolled(studentId, courseId)) {
            // Student is already enrolled, prevent duplicate enrollment
            return false;
        }

        String insertQuery = "INSERT INTO enrollment (student_id, course_id, enrollment_date) VALUES (?, ?, current_date)";

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement st = con.prepareStatement(insertQuery);
            st.setInt(1, studentId);
            st.setString(2, courseId);

            int rowsAffected = st.executeUpdate();
            con.close();

            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //method to get available course from the database
    public List<Map<String, Object>> getAvailableCourses(int studentId) {
    	DatabaseConfig config = new DatabaseConfig();
    	String url = config.getDbUrl();
    	String user = config.getDbUser();
    	String pwd = config.getDbPassword();
        String query = "SELECT course_id, course_name, credit_hours " +
                       "FROM course " +
                       "WHERE course_id NOT IN " +
                       "(SELECT course_id FROM enrollment WHERE student_id = ?)";
        List<Map<String, Object>> availableCourses = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);

            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, studentId); // Set student_id as an integer
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Map<String, Object> course = new HashMap<>();
                course.put("courseId", rs.getString("course_id"));
                course.put("courseName", rs.getString("course_name"));
                course.put("creditHours", rs.getInt("credit_hours"));
                // Add other course details as needed
                availableCourses.add(course);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return availableCourses;
    }

}
