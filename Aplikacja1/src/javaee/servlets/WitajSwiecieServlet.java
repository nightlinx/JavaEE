package javaee.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WitajSwiecieServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{		
		System.out.println("bla");
		res.setContentType("text/plain; charset=utf-8");
		res.getWriter().println("Witaj świecie Servlet");
		RequestDispatcher rd = req.getRequestDispatcher("/users");
		//rd.forward(req, res);	//przekazanie - drugi servlet calkowiie zajmie sie żądaniem. Ten utraci taką możliwosć
		 rd.include(req, res); //ten servlet obudowuje to co wygeneruje drugi servlet
		System.out.println("Zegnaj świecie Servlet");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{		
	}
	
}
