package com.loginServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/registerServlet")
public class Register extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();


		String name = req.getParameter("name");
		String password = req.getParameter("password");

		if(name == null || name.equals("") || name.equals(" ") || password == null || password.equals("") || password.equals(" ")){
			req.getRequestDispatcher("register.jsp").include(req, resp);
			out.println("<br><font color=red>" + "Please write correctly" + "</font>");
			out.close();

		}
		
		else{
			req.getRequestDispatcher("register.jsp").include(req, resp);
			registerLogin(name, password);
			out.println("<br><font color=blue>" + "Registration successful!" + "</font>");

		}
		
		out.close();
	}



	public boolean registerLogin(String email, String password){
		String sql = "INSERT INTO user(email, password) VALUES (?, ?)";

		Connection conn = (Connection) getServletContext().getAttribute("DBConn");

		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);

			stmt.executeUpdate();

		}
		catch(SQLException e) {

			e.printStackTrace();
			return false;
		}
		return false;

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		doGet(req, resp);

	}




}

