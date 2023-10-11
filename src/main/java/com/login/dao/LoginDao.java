package com.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.login.DatabaseConfig;

public class LoginDao {
	
	public boolean checkStudent(String uname,int password) {
		DatabaseConfig config = new DatabaseConfig();
		String url = config.getDbUrl();
		String user = config.getDbUser();
		String pwd = config.getDbPassword();
        String query = "SELECT * FROM STUDENT WHERE sid=? AND fname=?";
        	
		try {
			Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pwd);//connecting to Database
            PreparedStatement st = con.prepareStatement(query);
            st.setString(2, uname);
            st.setInt(1,password);
            ResultSet rs =st.executeQuery();
            if(rs.next()) {
            	return true;
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkInstructor( String uname,int password) {
		DatabaseConfig config = new DatabaseConfig();
		String url = config.getDbUrl();
		String user = config.getDbUser();
		String pwd = config.getDbPassword();
	    String query = "SELECT * FROM INSTRUCTOR WHERE instructor_id = ? AND first_name = ?";
	        
	    try {
	        Class.forName("org.postgresql.Driver");
	        Connection con = DriverManager.getConnection(url, user, pwd);
	        PreparedStatement st = con.prepareStatement(query);
            st.setString(2, uname);
            st.setInt(1,password);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
