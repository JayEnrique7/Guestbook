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

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profileservlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		req.getRequestDispatcher("logout.html").include(req, resp);
		req.getRequestDispatcher("profile.html").include(req, resp);
		String sql = "SELECT text FROM texts WHERE owner=?";
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		Connection conn = (Connection) getServletContext().getAttribute("DBConn");

		try{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				out.println("* ");
				out.println(rs.getString("text"));
				out.println("<br>");
			}
		}
		catch(SQLException e){

			e.printStackTrace();
		}

	}

}
