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
 * Servlet implementation class ProfileServlet, this class show the textarea and messengers from the user.
 */
@WebServlet("/profileservlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = resp.getWriter();
		String sqlName = "SELECT * FROM user WHERE id=?;";
		String sql = "SELECT text FROM texts WHERE owner=?";
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		Connection conn = (Connection) getServletContext().getAttribute("DBConn");

		try{
			PreparedStatement stmt1 = conn.prepareStatement(sqlName);
			PreparedStatement stmt2 = conn.prepareStatement(sql);
			stmt1.setInt(1, id);
			stmt2.setInt(1, id);

			ResultSet rs1 = stmt1.executeQuery();
			ResultSet rs2 = stmt2.executeQuery();

			while(rs1.next()){

				req.getRequestDispatcher("logout.html").include(req, resp);
				out.println("<b><font color=red>The " + rs1.getString("email") + "'s guestbook</font></b>");
				req.getRequestDispatcher("profile.html").include(req, resp);
				out.println("<br>");

				rs2.afterLast();
				while(rs2.previous()){

					out.println("<font color='white'>° " + rs2.getString("text") + "</font><br>");

				}		
			}	
		}

		catch(SQLException e){

			e.printStackTrace();
		}
		out.close();
	}
}
