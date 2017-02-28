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
// wytworzenie w ramach metody obsługi (doGet) nowego wątku i wykonanie pewnej
// długotrwałej czynności np połącznie z bazą danych albo skorzystanie z obsługi sieciowej w nowym wątku
// @ServletSecurity(httpMethodConstraints={ @HttpMethodConstraint(value="GET", rolesAllowed = {"admin", "moderator"}, transportGuarantee = TransportGuarantee.NONE)})

public class WitajSwiecieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		ServletContext context = getServletContext();		
		DatabaseConnector dc = new DatabaseConnector();		
		String path = dc.connect(context.getRealPath("/WEB-INF/configuration.txt"));
		res.setContentType("text/plain; charset=utf-8");
		
		Driver sterownik;
		String query = "select idAnimals, Name, color from animals";
		try {
			sterownik = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(sterownik);
			Connection conn = sterownik.connect(path, null);
			viewTable(conn,query, res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
		public static void viewTable(Connection con, String query, HttpServletResponse res)
			    throws SQLException, IOException {

			    Statement stmt = null;		    
			    try {
			        stmt = con.createStatement();
			        ResultSet rs = stmt.executeQuery(query);
			        int id;
			        String name;
	        		int age;		        
			        String color;
			        while (rs.next()) {
			        	if(query.contains("idAnimals")){
			        		id = rs.getInt("idAnimals");	
			        		res.getWriter().print(id + "\t");
			        	}
			        	if(query.contains("Name")){			        	
			        		name = rs.getString("Name");
			        		res.getWriter().print(name + "\t");
			        	}
			        	if(query.contains("Age")){				        	
			        		age = rs.getInt("Age");
			        		res.getWriter().print(age + "\t");
			        	}
			        	if(query.contains("color")){	
			        		color = rs.getString("color");
			        		res.getWriter().print(color + "\t");
			        	}
			        	res.getWriter().println();
			        }
			    } catch (SQLException e ) {
			    	e.printStackTrace();
			    } finally {
			        if (stmt != null) {
			        	stmt.close(); 
			        }
			    }
			}
		
		
		
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	}

}
