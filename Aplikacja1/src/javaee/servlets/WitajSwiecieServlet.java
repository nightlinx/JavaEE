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
		
		try{
			Driver sterownik = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(sterownik);
		
			Connection connection = sterownik.connect(path, null);
			Statement stmt = connection.createStatement(); //tworzymy zapytanie
			if(stmt.execute("SELECT * from animals")){ // ZAPYTANIE POBIERAJACE - pobierz wszystko z tabeli uzytkownik
				ResultSet zbior = stmt.getResultSet(); //ZAPYTANIE MODYFIKUJACE - zwracaja liczbe wynikow
				while(zbior.next()){
					res.getWriter().println(zbior.getString("idAnimals")+" "+zbior.getString("Name")+" " + zbior.getString("Age") + "\n");
				}
		}
			connection.close();
		}catch (SQLException e){
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
	}

}
