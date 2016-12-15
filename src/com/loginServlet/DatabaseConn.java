package com.loginServlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConn{
	
	
	
	private Connection conn;
	
	public DatabaseConn() throws ClassNotFoundException, SQLException{
		
		String URL = "jdbc:mysql://localhost/guest_book";
		String USER = "root";
		String PSW = "";
		
		Class.forName("com.mysql.jdbc.Driver");
		this.conn = DriverManager.getConnection(URL, USER, PSW);
		
		
	}
	
	
	public Connection getConnetion(){
		
		return this.conn;
	}
	
	

	}
	
	
	


