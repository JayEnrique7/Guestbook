package com.loginServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		/**
		 * A class check out if the name and password is correctly, and this send a session to the user.
		 */

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();


		String name =  req.getParameter("name");
		String password = req.getParameter("password");


		if(validLogin (name, password, req)){

			req.getRequestDispatcher("link.html").include(req, resp);
			out.print("<h2><font color=white>Welcome " + name + "</font></h2>");
			HttpSession session = req.getSession();
			int id = (int) req.getAttribute("id");
			session.setAttribute("name", name);
			session.setAttribute("id", id);

			out.close();

		}

		if(name == null && password == null || name == null && password.equals("") || name == null && password.equals("") || name.equals("") && password == null ||
				name.equals("") && password.equals("") || name.equals("") && password.equals(" ") || name.equals(" ") && password == null || 
				name.equals(" ") && password.equals("") || name.equals(" ") && password.equals(" ")){

			req.getRequestDispatcher("login.jsp").include(req, resp);
			out.println("<font color=red><h2>" + "Come on dude! Write correctly" + "</h2></font>");
			out.close();

		}

		if(name == null || name.equals("") || name == (" ")){

			req.getRequestDispatcher("login.jsp").include(req, resp);
			out.println("<font color=red><h2>" + "Are you crazy!?, No name!? come on dude write correctly!" + "</h2></font>");
			out.close();

		}

		if(password == null || password.equals("") || password.equals(" ")){
			req.getRequestDispatcher("login.jsp").include(req, resp);
			out.println("<font color=red><h2>" + "Are you crazy!?, No password!? come on dude write correctly!" + "</h2></font>");
			out.close();
		}

		else{
			req.getRequestDispatcher("login.jsp").include(req, resp);
			out.println("<font color=red><h2>" + "Wrong name or password dude! Try again." + "</h2></font>");

		}
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	/**
	 *A boolean class, this ask the database if the name and password is true or false.
	 */

	public boolean validLogin(String email, String password, HttpServletRequest req) {

		String sql = "SELECT * FROM user WHERE email=? AND password=?";
		Connection conn = (Connection) getServletContext().getAttribute("DBConn");

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			if(rs.next()){
				req.setAttribute("id", rs.getInt("id"));
				return true;
			}
			else {
				return false;
			}
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
