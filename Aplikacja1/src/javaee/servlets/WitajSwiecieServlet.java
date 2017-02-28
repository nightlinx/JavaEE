package javaee.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.sql.*;

import javax.servlet.AsyncContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaee.other.DatabaseConnector;
import javaee.other.User;

@WebServlet(urlPatterns = { "/hello" }, asyncSupported = true)

public class WitajSwiecieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		res.setContentType("text/plain; charset=utf-8");
		
		ServletContext context = getServletContext();		
		DatabaseConnector dc = new DatabaseConnector();

		String query = "select idAnimals, Name, color from animals";
		try {
			Connection conn = dc.getConnection(context.getRealPath("/WEB-INF/configuration.txt"));
			viewTable(conn,query, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("pyklo");
	}

	
	public static void viewTable(Connection con, String query, HttpServletResponse res)
			throws SQLException, IOException {

		try (Statement stmt = con.createStatement()) {

			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				if (query.contains("idAnimals")) {
					int id = rs.getInt("idAnimals");
					res.getWriter().print(id + "\t");
				}
				if (query.contains("Name")) {
					String name = rs.getString("Name");
					res.getWriter().print(name + "\t");
				}
				if (query.contains("Age")) {
					int age = rs.getInt("Age");
					res.getWriter().print(age + "\t");
				}
				if (query.contains("color")) {
					String color = rs.getString("color");
					res.getWriter().print(color + "\t");
				}
				res.getWriter().println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	}

}
