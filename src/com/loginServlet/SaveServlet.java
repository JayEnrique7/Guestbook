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
 * Servlet implementation class SaveServlet
 */
@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		PrintWriter out = resp.getWriter();
		req.getRequestDispatcher("logout.html").include(req, resp);

		String sql = "INSERT INTO texts(text, owner) VALUES (?, ?)";
		String sqlGet = "SELECT text FROM texts WHERE owner=?";
		String text = req.getParameter("textarea");
		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		Connection conn = (Connection) getServletContext().getAttribute("DBConn");

		try {

			PreparedStatement stmt = conn.prepareStatement(sql);
			PreparedStatement stmt2 = conn.prepareStatement(sqlGet);
			stmt.setString(1,text);
			stmt.setInt(2, id);
			stmt2.setInt(1, id);

			stmt.executeUpdate();
			req.getRequestDispatcher("profile.html").include(req, resp);
			ResultSet rs = stmt2.executeQuery();
			while(rs.next()){
				out.println("* ");
				out.println(rs.getString("text"));
				out.println("<br>");

			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		out.close();
	}
	// TODO Auto-generated method stub


}
