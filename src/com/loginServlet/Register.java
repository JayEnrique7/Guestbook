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
/**
 * this class register new users and check out if the user inserte a name and a password with help from the boolean class
 */

@WebServlet("/registerServlet")
public class Register extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String name = req.getParameter("name");
		String password = req.getParameter("password");

		if(name == null && password == null || name == null && password.equals("") || name == null && password.equals("") || name.equals("") && password == null ||
				name.equals("") && password.equals("") || name.equals("") && password.equals(" ") || name.equals(" ") && password == null || 
				name.equals(" ") && password.equals("") || name.equals(" ") && password.equals(" ")){

			req.getRequestDispatcher("register.jsp").include(req, resp);
			out.println("<br><h2><font color=red>" + "Come on dude! Be serious and write correctly." + "</font></h2>");
			out.close();
		}

		if(name == null || name.equals("") || name == (" ")){

			req.getRequestDispatcher("register.jsp").include(req, resp);
			out.println("<br><h2><font color=red>" + "Are you crazy!?, No name!? come on dude insert your name..." + "</font></h2>");
			out.close();
		}

		if(password == null || password.equals("") || password.equals(" ")){
			req.getRequestDispatcher("register.jsp").include(req, resp);
			out.println("<br><h2><font color=red>" + "Are you crazy!?, No password!? come on dude insert your password..." + "</font></h2>");
			out.close();
		}

		else{
			req.getRequestDispatcher("register.jsp").include(req, resp);
			registerLogin(name, password);
			out.println("<br><h2><font color=blue>" + "OMG! You're a legend! Registration successful!<br>Go to login page." + "</font></h2>");
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
