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

public class WitajSwiecieServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter pw = res.getWriter();
		
		if(req.getCookies() != null){
			for(Cookie c : req.getCookies()){
				if(c.getName().equals("name")){
					pw.println("Pamietam cie, " + c.getValue() + "!!");
				}
			}
		}
		//eraseCookie(req, res);
		
		pw.println("<html><head><title>OTO MOJA STRONA</title></head>");
		pw.println("<body>");
		pw.println("<form method=\"post\">");
		pw.println("<p>Podaj imie</p> <input name=\"name\"/><input type=\"submit\" value=\"OK\"/>");
		pw.println("</form>");
		pw.println("</body></html>");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		res.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = res.getWriter();
		String name = req.getParameter("name");
		
		if(name != null && !name.equals("")){
			Cookie ciasteczko = new Cookie("name", name);
			ciasteczko.setMaxAge(60);
			res.addCookie(ciasteczko);
			pw.println("Siemaneczko, "+name);			
		}
	}
	
	private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
	    Cookie[] cookies = req.getCookies();
	    if (cookies != null)
	        for (int i = 0; i < cookies.length; i++) {
//	            cookies[i].setValue("");
//	            cookies[i].setPath("/");
	            cookies[i].setMaxAge(0);
//	            resp.addCookie(cookies[i]);
	        }
	}

}
