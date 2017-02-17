package javaee.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

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
		System.out.println("witajswiecieservlet");
		User u = new User();
		u.setId(1);
		u.setUserName("Ania");
		u.setSurname("Lis");
		req.setAttribute("user1", u);
		
		User u2 = new User();
		u2.setId(1);
		u2.setUserName("Janek");
		u2.setSurname("Nowak");
		req.setAttribute("allUsers", Arrays.asList(u, u2));
		
		//res.setContentType("text/plain; charset=utf-8");
		RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
		rd.forward(req, res);
		
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{		
	}
	
}
