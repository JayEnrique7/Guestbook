package com.loginServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//test
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveServlet, save the messengers and update.
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

		String sql = "INSERT INTO texts(text, owner) VALUES (?, ?)";
		String sqlGet = "SELECT text FROM texts WHERE owner=?";
		String text = req.getParameter("textarea");		
		String sqlName = "SELECT * FROM user WHERE id=?;";

		HttpSession session = req.getSession();
		int id = (int) session.getAttribute("id");
		Connection conn = (Connection) getServletContext().getAttribute("DBConn");
		
		/**
		 * the if method blocked blank messengers.
		 */

		if(text == null || text.equals("") || text.equals(" ")){

			try {

				PreparedStatement stmtName = conn.prepareStatement(sqlName);
				PreparedStatement stmt = conn.prepareStatement(sql);
				PreparedStatement stmt2 = conn.prepareStatement(sqlGet);

				stmtName.setInt(1, id);
				stmt.setString(1, text);
				stmt.setInt(2, id);
				stmt2.setInt(1, id);

				ResultSet rsName = stmtName.executeQuery();
				ResultSet rs = stmt2.executeQuery();

				while(rsName.next()){

					req.getRequestDispatcher("logout.html").include(req, resp);
					out.println("<b><font color='red'>The " + rsName.getString("email") + "'s guestbook</font></b>");
					req.getRequestDispatcher("profile.html").include(req, resp);
					out.println("<font color='red'>You can't leave a blank greeting, come dude, leave a cute message</font><br>");

					rs.afterLast();
					while(rs.previous()){
						out.println("<font color='white'>° " + rs.getString("text") + "</font><br>");
					}

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			out.close();
		}

		else{

			try {

				PreparedStatement stmtName = conn.prepareStatement(sqlName);
				PreparedStatement stmt = conn.prepareStatement(sql);
				PreparedStatement stmt2 = conn.prepareStatement(sqlGet);

				stmtName.setInt(1, id);
				stmt.setString(1, text);
				stmt.setInt(2, id);
				stmt2.setInt(1, id);
				stmt.executeUpdate();

				ResultSet rsName = stmtName.executeQuery();
				ResultSet rs = stmt2.executeQuery();

				while(rsName.next()){

					req.getRequestDispatcher("logout.html").include(req, resp);
					out.println("<b><font color=red>The " + rsName.getString("email") + "'s guestbook</font></b>");
					req.getRequestDispatcher("profile.html").include(req, resp);
					out.println("<br>");

					rs.afterLast();
					while(rs.previous()){
						out.println("<font color='white'>° " + rs.getString("text") + "</font><br>");
					}
				}
			}

			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.close();
			// TODO Auto-generated method stub
		}
	}
}
