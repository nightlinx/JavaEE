package javaee.servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WitajSwiecieServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		res.setContentType("text/plain; charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("Witaj świecie");
		
		res.setContentType("text/html; charset=utf-8");
		pw.println("<html><head><title>OTO MOJA STRONA</title></head>");
		pw.println("<body>");
		pw.println("<p>Wy też witajcie!</p>");
		pw.println("</body></html>");
	}
}
