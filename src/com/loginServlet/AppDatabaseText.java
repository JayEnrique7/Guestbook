package com.loginServlet;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.loginServlet.DatabaseConn;

@WebListener
public class AppDatabaseText  implements ServletContextListener{

	public void contextInitialized(ServletContextEvent servletContextEvent){
		ServletContext ctx = servletContextEvent.getServletContext();

		try {
			DatabaseConn databaseConn = new DatabaseConn();
			ctx.setAttribute("DBConn", databaseConn.getConnetion());



		} catch(ClassNotFoundException e){
			e.printStackTrace();

		}catch(SQLException e){
			e.printStackTrace();
		}

	}

	
	public void contextDestroyed(ServletContextEvent servletContextEvent){
		
		Connection conn = (Connection) servletContextEvent.getServletContext().getAttribute("DBConn");
		
		
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}




}





