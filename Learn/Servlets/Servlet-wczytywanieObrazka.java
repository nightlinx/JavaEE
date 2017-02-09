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
		byte[] bufor = readFile("C:\\Users\\Anna\\Desktop\\kaktusy.jpg");
		res.setContentType("image/jpg");
		res.setContentLength(bufor.length);
		res.addHeader("Content-Disposition", "attachment; filename=kaktusy.jpg"); //attachment = obrazek w formie zalacznika. 
		OutputStream os = res.getOutputStream(); //strumien bajtowy
		os.write(bufor);
		os.flush();
	}
	
	private byte[] readFile(String fileName){
		File f = new File(fileName);
		long size = f.length();
		byte[] file = new byte[(int)size];
		try {
			FileInputStream fis = new FileInputStream(f);
			fis.read(file);
			fis.close();
		} catch (IOException e) {
			System.out.println("Nie udalo sie wczytac pliku");
			e.printStackTrace();
		}
		return file;
	}
}
