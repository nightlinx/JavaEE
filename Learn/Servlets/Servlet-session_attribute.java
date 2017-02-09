package javaee.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WitajSwiecieServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter pw = res.getWriter();
		
		HttpSession session = req.getSession();
		session.setMaxInactiveInterval(10);;
		Integer counter = (Integer)session.getAttribute("counter");
		if(counter == null){
			counter = 1;
		}
		else{
			counter++;
		}
		session.setAttribute("counter", counter); //to jest po to, by pamietac ilosc wejsc przy kolejnych wejsciach do metody
		pw.println("Byles na stronie tyle razy: " + counter);
	}
}
