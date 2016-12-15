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

	/*
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub


		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();


		String name =  req.getParameter("name");
		String password = req.getParameter("password");



		if(name == null || name.equals("") || name == (" ") || password == null || password.equals("") || password.equals(" ")){

			req.getRequestDispatcher("login.jsp").include(req, resp);
			out.println("<font color=red>" + "Try again!" + "</font>");
			out.close();

		}

		if(validLogin (name, password, req)){

			req.getRequestDispatcher("link.html").include(req, resp);
			out.print("Welcome " + name);
			HttpSession session = req.getSession();
			int id = (int) req.getAttribute("id");
			session.setAttribute("name", name);
			session.setAttribute("id", id);

			out.close();

		}

		else{
			req.getRequestDispatcher("login.jsp").include(req, resp);
			out.println("<font color=red>" + "Wrong name or password dude! Try again." + "</font>");

		}
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}



}





