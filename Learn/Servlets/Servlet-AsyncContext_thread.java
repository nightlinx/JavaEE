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
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaee.other.User;

@WebServlet(urlPatterns = { "/hello"}, asyncSupported = true)
//wytworzenie w ramach metody obsługi (doGet) nowego wątku i wykonanie pewnej długotrwałej czynności np połącznie z bazą danych
//albo skorzystanie z obsługi sieciowej w nowym wątku
//@ServletSecurity(httpMethodConstraints={ @HttpMethodConstraint(value="GET", rolesAllowed = {"admin", "moderator"}, transportGuarantee = TransportGuarantee.NONE)}) 

public class WitajSwiecieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{	
		final AsyncContext context = req.startAsync(req,res);
		
		new Thread(new Runnable() { //to sie bedzie wykonywac 3s, ale metoda doGet juz skonczy swoje dzialanie
			
			@Override
			public void run() {
				context.getResponse().setContentType("text/plain; charset=utf-8");
				try {
					Thread.sleep(30);
					context.getResponse().getWriter().println("Hello you!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();	
		System.out.println("doget");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{		
	}
	
}
