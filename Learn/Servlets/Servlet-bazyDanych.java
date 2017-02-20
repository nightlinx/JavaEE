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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaee.other.User;

public class WitajSwiecieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{	
		res.setContentType("text/plain; charset=utf-8");
		try{
		Driver sterownik = new com.mysql.jdbc.Driver();
		DriverManager.registerDriver(sterownik);
		Connection connection = sterownik.connect("jdbc:mysql://localhost/szkolenie_javaee?user=root&amp;password=&characterEncording=utf8", null);
		Statement stmt = connection.createStatement(); //tworzymy zapytanie
		if(stmt.execute("SELECT * from uzytkownik")){ // ZAPYTANIE POBIERAJACE - pobierz wszystko z tabeli uzytkownik
			ResultSet zbior = stmt.getResultSet(); //ZAPYTANIE MODYFIKUJACE - zwracaja liczbe wynikow
			while(zbior.next()){
				res.getWriter().println(zbior.getString("imie")+" " + zbior.getString("nazwisko") + "\n");
			}
		}
		}catch (SQLException e){
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{		
	}
	
}
